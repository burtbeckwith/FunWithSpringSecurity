package hacking.extralogin.auth

import grails.plugin.springsecurity.userdetails.GormUserDetailsService
import grails.plugin.springsecurity.userdetails.GrailsUser
import grails.plugin.springsecurity.userdetails.NoStackUsernameNotFoundException
import groovy.transform.CompileStatic
import groovy.transform.TypeCheckingMode
import groovy.util.logging.Slf4j
import hacking.OrgUser
import hacking.User
import hacking.extralogin.OrganizationAuthentication
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider
import org.springframework.security.authentication.dao.SaltSource
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService

/**
 * Based on DaoAuthenticationProvider, but directly accesses the database instead of delegating to a UserDetailsService.
 */
@CompileStatic
@Slf4j
class OrganizationAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	// The plaintext password used to perform PasswordEncoder.isPasswordValid()
	// on when the user is not found to avoid SEC-2056
	protected static final String USER_NOT_FOUND_PASSWORD = 'userNotFoundPassword'

	// The password used to perform PasswordEncoder#isPasswordValid()
	// on when the user is not found to avoid SEC-2056
	protected String userNotFoundEncodedPassword

	// Dependency injected properties
	@SuppressWarnings('deprecation')
	org.springframework.security.authentication.encoding.PasswordEncoder passwordEncoder
	SaltSource saltSource
	UserDetailsService userDetailsService

	@CompileStatic(TypeCheckingMode.SKIP)
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken auth) throws AuthenticationException {

		String organizationName = ((OrganizationAuthentication)auth).organizationName

		// use withTransaction to avoid lazy loading exceptions
		User.withTransaction { status ->
			User user = OrgUser.createCriteria().get {
				organization {
					eq 'name', organizationName
				}
				user {
					eq 'username', username
				}
				projections {
					property 'user'
				}
			}

			if (!user) {
				def password = auth.credentials
				if (password != null) {
					passwordEncoder.isPasswordValid(userNotFoundEncodedPassword, password.toString(), null)
				}
				log.warn "User not found: $username in organization $organizationName"
				if (hideUserNotFoundExceptions) {
					throw new NoStackBadCredentialsException(messages.getMessage(
							'AbstractUserDetailsAuthenticationProvider.badCredentials',
							'Bad credentials'))
				}
				else {
					throw new NoStackUsernameNotFoundException()
				}
			}

			Collection<GrantedAuthority> authorities = user.authorities.collect { new SimpleGrantedAuthority(it.authority) } ?:
					[GormUserDetailsService.NO_ROLE]

			new GrailsUser(user.username, user.password, user.enabled, !user.accountExpired,
					!user.passwordExpired, !user.accountLocked, authorities, user.id)
		}
	}

	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {

		def salt = saltSource.getSalt(userDetails)

		if (authentication.credentials == null) {
			log.debug 'Authentication failed: no credentials provided'
			throw new NoStackBadCredentialsException(messages.getMessage(
					'AbstractUserDetailsAuthenticationProvider.badCredentials',
					'Bad credentials'))
		}

		String presentedPassword = authentication.credentials
		if (!passwordEncoder.isPasswordValid(userDetails.password, presentedPassword, salt)) {
			log.debug 'Authentication failed: password does not match stored value'

			throw new NoStackBadCredentialsException(messages.getMessage(
					'AbstractUserDetailsAuthenticationProvider.badCredentials',
					'Bad credentials'))
		}
	}

	protected void doAfterPropertiesSet() {
		userNotFoundEncodedPassword = passwordEncoder.encodePassword(USER_NOT_FOUND_PASSWORD, null)
	}

	boolean supports(Class<?> authenticationClass) {
		OrganizationAuthentication.isAssignableFrom authenticationClass
	}
}

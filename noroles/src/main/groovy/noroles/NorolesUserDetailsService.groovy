package noroles

import grails.plugin.springsecurity.SpringSecurityUtils
import grails.plugin.springsecurity.userdetails.GrailsUserDetailsService
import grails.plugin.springsecurity.userdetails.NoStackUsernameNotFoundException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import test.User

class NorolesUserDetailsService implements GrailsUserDetailsService {

	static final List NO_ROLES = [new SimpleGrantedAuthority(SpringSecurityUtils.NO_ROLE)]

	UserDetails loadUserByUsername(String username, boolean loadRoles) throws UsernameNotFoundException {
		return loadUserByUsername(username)
	}

	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User.withTransaction {
			User user = User.findByUsername(username)
			if (!user) throw new NoStackUsernameNotFoundException()

			def authorities = user.authorities.collect { new SimpleGrantedAuthority(it.authority) }
			new NorolesGrailsUser(user.username, user.password, user.enabled,
					!user.accountExpired, !user.passwordExpired,
					!user.accountLocked, authorities ?: NO_ROLES, user.id,
					user.userType, user.businessUnit)
		}
	}
}

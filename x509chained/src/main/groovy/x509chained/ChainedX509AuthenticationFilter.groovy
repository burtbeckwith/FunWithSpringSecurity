package x509chained

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken
import org.springframework.security.web.authentication.preauth.x509.X509AuthenticationFilter

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@CompileStatic
@Slf4j
class ChainedX509AuthenticationFilter extends X509AuthenticationFilter {

	public static final String ROLE_CHAINED_X509 = 'ROLE_CHAINED_X509'

	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication auth) {
		log.debug 'Authentication success: {}', auth

		// create a copy of the auth but with the authories replaced with a marker instance
		SecurityContextHolder.context.authentication = new PreAuthenticatedAuthenticationToken(
			auth.principal, auth.credentials, [new SimpleGrantedAuthority(ROLE_CHAINED_X509)])
	}
}

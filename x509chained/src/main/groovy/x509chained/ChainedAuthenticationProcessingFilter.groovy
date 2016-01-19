package x509chained

import grails.plugin.springsecurity.web.authentication.GrailsUsernamePasswordAuthenticationFilter
import groovy.transform.CompileStatic
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken

import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@CompileStatic
class ChainedAuthenticationProcessingFilter extends GrailsUsernamePasswordAuthenticationFilter {

	public static final String REDIRECTED = 'REDIRECTED'

	AuthenticationEntryPoint authenticationEntryPoint

	void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		super.doFilter req, res, new FilterChain() {
			void doFilter(ServletRequest request, ServletResponse response) throws IOException, ServletException {
				if (shouldRedirect()) {
					// replace the credentials with a marker so after the redirect we can detect that the redirect happened
					def auth = SecurityContextHolder.context.authentication
					SecurityContextHolder.context.authentication = new PreAuthenticatedAuthenticationToken(
							auth.principal, 'REDIRECTED', auth.authorities)
				}
				else {
					// only proceed down the filter chain if we haven't redirected
					chain.doFilter request, response
				}
			}
		}
	}

	protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
		if (shouldRedirect()) {
			// send a redirect to the login form
			authenticationEntryPoint.commence request, response, null
			// return false so chain.doFilter() is called
			return false
		}

		super.requiresAuthentication request, response
	}

	protected boolean shouldRedirect() {
		Authentication auth = SecurityContextHolder.context.authentication
		if (!auth) {
			return false
		}

		auth.authorities.size() == 1 &&
		auth.authorities.iterator().next().authority == ChainedX509AuthenticationFilter.ROLE_CHAINED_X509 &&
		auth.credentials != REDIRECTED
	}
}

package hacking.extralogin.ui

import grails.plugin.springsecurity.SpringSecurityUtils
import grails.plugin.springsecurity.web.authentication.GrailsUsernamePasswordAuthenticationFilter
import groovy.transform.CompileStatic
import hacking.extralogin.OrganizationAuthentication
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession

@CompileStatic
class OrganizationFilter extends GrailsUsernamePasswordAuthenticationFilter {

	protected boolean postOnly

	@Override
	Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

		if (postOnly && request.method != 'POST') {
			throw new AuthenticationServiceException("Authentication method not supported: $request.method")
		}

		String username = (obtainUsername(request) ?: '').trim()
		String password = obtainPassword(request) ?: ''
		String orgName = request.getParameter('orgName')

		if (storeLastUsername) {
			// Place the last username attempted into HttpSession for views
			HttpSession session = request.getSession(false)
			if (!session && allowSessionCreation) {
				session = request.session
			}

			session?.setAttribute SpringSecurityUtils.SPRING_SECURITY_LAST_USERNAME_KEY, username
		}

		Authentication authentication = new OrganizationAuthentication(username, password, orgName)
		setDetails request, authentication
		authenticationManager.authenticate authentication
	}

	@Override
	void setPostOnly(boolean postOnly) {
		super.setPostOnly postOnly
		this.postOnly = postOnly
	}
}

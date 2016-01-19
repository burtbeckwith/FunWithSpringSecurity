package lockout

import grails.plugin.springsecurity.userdetails.GrailsUser
import org.springframework.context.ApplicationListener
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.authentication.event.AuthenticationSuccessEvent
import org.springframework.security.core.Authentication

class SuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {

	def userService

	void onApplicationEvent(AuthenticationSuccessEvent event) {
		Authentication authentication = event.authentication
		assert authentication instanceof UsernamePasswordAuthenticationToken
		assert authentication.principal instanceof GrailsUser
		String username = authentication.principal.username

		userService.onSuccessfulLogin username
	}
}

package lockout

import org.springframework.context.ApplicationListener
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent
import org.springframework.security.core.Authentication

class FailureEventListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

	def userService

	void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
		Authentication authentication = event.authentication
		assert authentication instanceof UsernamePasswordAuthenticationToken
		String username = authentication.principal

		userService.onBadCredentials username
	}
}

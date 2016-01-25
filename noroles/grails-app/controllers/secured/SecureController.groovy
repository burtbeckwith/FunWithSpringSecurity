package secured

import grails.plugin.springsecurity.annotation.Secured

class SecureController {

	@Secured('ROLE_NO_ROLES')
	def index() {
		render "You have ROLE_NO_ROLES: $principal"
	}
}
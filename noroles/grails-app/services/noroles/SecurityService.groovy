package noroles

import grails.transaction.Transactional

class SecurityService {

	def springSecurityService

	@Transactional(readOnly=true)
	boolean isDeveloper() {
		springSecurityService.currentUser?.developer
	}
}

import hacking.OrgUser
import hacking.Organization
import hacking.Role
import hacking.User
import hacking.UserRole

class BootStrap {

	def init = {
		def save = { it.save(failOnError: true) }

		def adminRole = save Role.findOrCreateByAuthority('ROLE_ADMIN')
		def userRole = save Role.findOrCreateByAuthority('ROLE_USER')

		def org1 = save Organization.findOrCreateByName('Org1')
		def org2 = save Organization.findOrCreateByName('Org2')

		if (!User.count()) {
			def admin = save new User('admin', 'password')
			save new OrgUser(org1, admin)
			UserRole.create admin, adminRole

			def user = save new User('user', 'password')
			save new OrgUser(org2, user)
			UserRole.create user, userRole

			def disabledUser = save new User(username: 'disabled', password: 'password', enabled: false)
			save new OrgUser(org1, disabledUser)
			UserRole.create disabledUser, userRole
		}

		UserRole.withSession {
			it.flush()
			it.clear()
		}

		assert Organization.count() == 2
		assert Role.count() == 2
		assert User.count() == 3
		assert UserRole.count() == 3
	}
}

import test.Role
import test.User
import test.UserRole

class BootStrap {

	def init = {
		def roleAdmin = new Role('ROLE_ADMIN').save(failOnError: true)
		def roleUser = new Role('ROLE_USER').save(failOnError: true)

		def dianne = new User('dianne', 'not_used').save(failOnError: true)
		UserRole.create dianne, roleAdmin

		def scott = new User('scott', 'not_used').save(failOnError: true)
		UserRole.create scott, roleUser

		User.withSession {
			it.flush()
			it.clear()
		}

		assert Role.count() == 2
		assert User.count() == 2
		assert UserRole.count() == 2
	}
}

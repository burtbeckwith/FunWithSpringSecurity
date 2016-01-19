import noroles.UserType
import test.User

class BootStrap {

	def init = {
		new User(username: 'admin1', password: 'password', userType: UserType.admin, businessUnit: 'group1').save(failOnError: true)
		new User(username: 'admin2', password: 'password', userType: UserType.admin, businessUnit: 'group2').save(failOnError: true)
		new User(username: 'salesdude', password: 'password', userType: UserType.sales, businessUnit: 'group1').save(failOnError: true)
		new User(username: 'codemonkey', password: 'password', userType: UserType.other, businessUnit: 'it', developer: true).save(failOnError: true)

		User.withSession {
			it.flush()
			it.clear()
		}

		assert User.count() == 4
	}
}

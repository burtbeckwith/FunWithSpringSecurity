import test.User

class BootStrap {

	def init = {
		new User(username: 'admin', password: 'password', admin: true).save(failOnError: true)
		new User('user', 'password').save(failOnError: true)

		User.withSession {
			it.flush()
			it.clear()
		}

		assert User.count() == 2
	}
}

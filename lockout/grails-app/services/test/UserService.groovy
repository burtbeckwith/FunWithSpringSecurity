package test

import grails.transaction.Transactional
import groovy.util.logging.Slf4j

@Slf4j
@Transactional
class UserService {

	void saveOrUpdate(User user) {
		user.save()
	}

	void delete(User user) {
		user.delete()
	}

	void onBadCredentials(String username) {
		User user = User.findByUsername(username)
		if (!user) {
			return
		}

		user.badCredentialsCount++
		if (user.badCredentialsCount > 3) {
			user.accountLocked = true
		}

		log.debug 'User {} badCredentialsCount: {} accountLocked: {}',
			username, user.badCredentialsCount, user.accountLocked

		user.save()
	}

	void onSuccessfulLogin(String username) {
		User user = User.findByUsername(username)
		if (user.badCredentialsCount) {
			user.badCredentialsCount = 0
			user.accountLocked = false
			user.save()
			log.debug 'User {} unlocked', username
		}
	}
}

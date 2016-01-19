package test

import static org.springframework.http.HttpStatus.*

import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_ADMIN')
class UserController {

	static allowedMethods = [save: 'POST', update: 'PUT', delete: 'DELETE']

	def userService

	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond User.list(params), model: [userCount: User.count()]
	}

	def show(User user) {
		respond user
	}

	def create() {
		respond new User(params)
	}

	def save(User user) {
		if (user == null) {
			notFound()
			return
		}

		if (user.hasErrors()) {
			respond user.errors, view: 'create'
			return
		}

		userService.saveOrUpdate user

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.created.message',
				                        args: [message(code: 'user.label', default: 'User'), user.id])
				redirect user
			}
			'*' { respond user, [status: CREATED] }
		}
	}

	def edit(User user) {
		respond user
	}

	def update(User user) {
		if (user == null) {
			notFound()
			return
		}

		if (user.hasErrors()) {
			respond user.errors, view: 'edit'
			return
		}

		userService.saveOrUpdate user

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.updated.message',
				                        args: [message(code: 'user.label', default: 'User'), user.id])
				redirect user
			}
			'*'{ respond user, [status: OK] }
		}
	}

	def delete(User user) {

		if (user == null) {
			notFound()
			return
		}

		userService.delete user

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.deleted.message',
				                        args: [message(code: 'user.label', default: 'User'), user.id])
				redirect action: 'index', method: 'GET'
			}
			'*'{ render status: NO_CONTENT }
		}
	}

	protected void notFound() {
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.not.found.message',
				                        args: [message(code: 'user.label', default: 'User'), params.id])
				redirect action: 'index', method: 'GET'
			}
			'*'{ render status: NOT_FOUND }
		}
	}
}

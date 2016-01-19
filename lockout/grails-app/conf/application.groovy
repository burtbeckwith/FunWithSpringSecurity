grails {
	plugin {
		springsecurity {
			authority.className = 'test.Role'
			gsp {
				layoutAuth = layoutDenied = 'application'
			}
			logout.postOnly = false
			roleHierarchy = 'ROLE_ADMIN > ROLE_USER'
			userLookup {
				authorityJoinClassName = 'test.UserRole'
				userDomainClassName = 'test.User'
			}

			dao.hideUserNotFoundExceptions = false
			useSecurityEventListener = true

			controllerAnnotations.staticRules = [
				[pattern: '/',               access: 'permitAll'],
				[pattern: '/error',          access: 'permitAll'],
				[pattern: '/favicon.ico',    access: 'permitAll'],
				[pattern: '/index',          access: 'permitAll'],
				[pattern: '/index.gsp',      access: 'permitAll'],
				[pattern: '/shutdown',       access: 'permitAll']
			]
		}
	}
}

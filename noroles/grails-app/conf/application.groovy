grails {
	plugin {
		springsecurity {
			gsp {
				layoutAuth = layoutDenied = 'application'
			}
			logout.postOnly = false
			userLookup.userDomainClassName = 'test.User'
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

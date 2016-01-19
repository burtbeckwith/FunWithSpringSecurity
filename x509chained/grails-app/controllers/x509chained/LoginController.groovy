package x509chained

import org.springframework.security.access.annotation.Secured

@Secured('permitAll')
class LoginController extends grails.plugin.springsecurity.LoginController {
	def index() {
		redirect action: 'auth', params: params
	}

	def auth() {
		def conf = getConf()
		String postUrl = request.contextPath + conf.apf.filterProcessesUrl
		render view: 'auth', model: [postUrl: postUrl,
		                             rememberMeParameter: conf.rememberMe.parameter,
		                             usernameParameter: conf.apf.usernameParameter,
		                             passwordParameter: conf.apf.passwordParameter,
		                             gspLayout: conf.gsp.layoutAuth]
	}
}

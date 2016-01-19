import grails.plugin.springsecurity.SpringSecurityUtils
import grails.plugin.springsecurity.web.authentication.AjaxAwareAuthenticationEntryPoint
import x509chained.ChainedX509BeanFactoryPostProcessor

beans = {

	def conf = SpringSecurityUtils.securityConfig

	ajaxAwareAuthenticationEntryPoint(AjaxAwareAuthenticationEntryPoint, conf.auth.loginFormUrl) { // '/login/auth'
		ajaxLoginFormUrl = conf.auth.ajaxLoginFormUrl // '/login/authAjax'
		forceHttps = conf.auth.forceHttps // false
		useForward = conf.auth.useForward // false
		portMapper = ref('portMapper')
		portResolver = ref('portResolver')
		redirectStrategy = ref('redirectStrategy')
	}

	chainedX509BeanFactoryPostProcessor(ChainedX509BeanFactoryPostProcessor)
}

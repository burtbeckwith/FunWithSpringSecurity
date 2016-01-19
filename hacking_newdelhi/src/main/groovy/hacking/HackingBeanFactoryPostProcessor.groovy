package hacking

import groovy.transform.CompileStatic
import hacking.extralogin.auth.OrganizationAuthenticationProvider
import hacking.extralogin.ui.OrganizationFilter
import hacking.logout.CustomLogoutSuccessHandler
import org.springframework.beans.BeansException
import org.springframework.beans.factory.config.BeanFactoryPostProcessor
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.beans.factory.support.GenericBeanDefinition

/**
 * CustomLogoutSuccessHandler, OrganizationAuthenticationProvider, and OrganizationFilter extend beans
 * from the core plugin and have the same dependency-injected properties, so rather than duplicating
 * everything in resources.groovy, this post-processor just updates the bean class in the BeanDefinition.
 */
@CompileStatic
class HackingBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		GenericBeanDefinition beanDefinition = (GenericBeanDefinition)beanFactory.getBeanDefinition('authenticationProcessingFilter')
		beanDefinition.beanClass = OrganizationFilter

		beanDefinition = (GenericBeanDefinition)beanFactory.getBeanDefinition('daoAuthenticationProvider')
		beanDefinition.beanClass = OrganizationAuthenticationProvider

		beanDefinition = (GenericBeanDefinition)beanFactory.getBeanDefinition('logoutSuccessHandler')
		beanDefinition.beanClass = CustomLogoutSuccessHandler
	}
}

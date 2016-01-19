package x509chained

import groovy.transform.CompileStatic
import org.springframework.beans.BeansException
import org.springframework.beans.factory.config.BeanFactoryPostProcessor
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.beans.factory.config.RuntimeBeanReference
import org.springframework.beans.factory.support.GenericBeanDefinition

/**
 * ChainedX509AuthenticationFilter and ChainedAuthenticationProcessingFilter extend beans
 * from the core plugin and have the same dependency-injected properties, so rather than duplicating
 * everything in resources.groovy, this post-processor just updates the bean class in the BeanDefinition.
 *
 * ChainedAuthenticationProcessingFilter also needs its own AuthenticationEntryPoint (enabling X.509
 * overrides the bean used by form-based auth) so that's wired up in resources.groovy and attached here.
 */
@CompileStatic
class ChainedX509BeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		GenericBeanDefinition beanDefinition = (GenericBeanDefinition)beanFactory.getBeanDefinition('x509ProcessingFilter')
		beanDefinition.beanClass = ChainedX509AuthenticationFilter

		beanDefinition = (GenericBeanDefinition)beanFactory.getBeanDefinition('authenticationProcessingFilter')
		beanDefinition.beanClass = ChainedAuthenticationProcessingFilter
		beanDefinition.propertyValues.add 'authenticationEntryPoint', new RuntimeBeanReference('ajaxAwareAuthenticationEntryPoint')
	}
}

package io.wiklandia.flex;

import javax.sql.DataSource;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import net.ttddyy.dsproxy.listener.logging.SLF4JLogLevel;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;

@Component
public class DatasourceProxyBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(final Object bean, final String beanName) {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(final Object bean, final String beanName) {
		if (bean instanceof DataSource) {
			DataSource dataSourceBean = (DataSource) bean;
			return ProxyDataSourceBuilder.create(dataSourceBean).logQueryBySlf4j(SLF4JLogLevel.INFO).build();
		}
		return bean;
	}

}

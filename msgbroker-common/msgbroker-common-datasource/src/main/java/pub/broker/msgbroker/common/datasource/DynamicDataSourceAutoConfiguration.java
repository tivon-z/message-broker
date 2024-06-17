/*
 * Copyright (c) 2020 PIG4CLOUD Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pub.broker.msgbroker.common.datasource;

import com.baomidou.dynamic.datasource.creator.DataSourceCreator;
import com.baomidou.dynamic.datasource.creator.DefaultDataSourceCreator;
import com.baomidou.dynamic.datasource.creator.hikaricp.HikariDataSourceCreator;
import com.baomidou.dynamic.datasource.processor.DsJakartaHeaderProcessor;
import com.baomidou.dynamic.datasource.processor.DsJakartaSessionProcessor;
import com.baomidou.dynamic.datasource.processor.DsProcessor;
import com.baomidou.dynamic.datasource.processor.DsSpelExpressionProcessor;
import com.baomidou.dynamic.datasource.provider.DynamicDataSourceProvider;
import pub.broker.msgbroker.common.datasource.config.ClearTtlDataSourceFilter;
import pub.broker.msgbroker.common.datasource.config.DataSourceProperties;
import pub.broker.msgbroker.common.datasource.config.JdbcDynamicDataSourceProvider;
import pub.broker.msgbroker.common.datasource.config.LastParamDsProcessor;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.expression.BeanFactoryResolver;

import java.util.ArrayList;
import java.util.List;

/**
 * @author broker.pub
 * @date 2020-02-06
 * <p>
 * 动态数据源切换配置
 */
@Configuration(proxyBeanMethods = false)
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
@EnableConfigurationProperties(DataSourceProperties.class)
public class DynamicDataSourceAutoConfiguration {

	/**
	 * 动态数据源提供者
	 * @param defaultDataSourceCreator 默认数据源创建器
	 * @param stringEncryptor 字符串加密器
	 * @param properties 数据源配置属性
	 * @return 动态数据源提供者
	 */
	@Bean
	public DynamicDataSourceProvider dynamicDataSourceProvider(DefaultDataSourceCreator defaultDataSourceCreator,
			StringEncryptor stringEncryptor, DataSourceProperties properties) {
		return new JdbcDynamicDataSourceProvider(defaultDataSourceCreator, stringEncryptor, properties);
	}

	/**
	 * 获取数据源处理器
	 * @return 数据源处理器
	 */
	@Bean
	public DsProcessor dsProcessor(BeanFactory beanFactory) {
		DsProcessor lastParamDsProcessor = new LastParamDsProcessor();
		DsProcessor headerProcessor = new DsJakartaHeaderProcessor();
		DsProcessor sessionProcessor = new DsJakartaSessionProcessor();
		DsSpelExpressionProcessor spelExpressionProcessor = new DsSpelExpressionProcessor();
		spelExpressionProcessor.setBeanResolver(new BeanFactoryResolver(beanFactory));
		lastParamDsProcessor.setNextProcessor(headerProcessor);
		headerProcessor.setNextProcessor(sessionProcessor);
		sessionProcessor.setNextProcessor(spelExpressionProcessor);
		return lastParamDsProcessor;
	}

	/**
	 * 默认数据源创建器
	 * @param hikariDataSourceCreator Hikari数据源创建器
	 * @return 默认数据源创建器
	 */
	@Bean
	public DefaultDataSourceCreator defaultDataSourceCreator(HikariDataSourceCreator hikariDataSourceCreator) {
		DefaultDataSourceCreator defaultDataSourceCreator = new DefaultDataSourceCreator();
		List<DataSourceCreator> creators = new ArrayList<>();
		creators.add(hikariDataSourceCreator);
		defaultDataSourceCreator.setCreators(creators);
		return defaultDataSourceCreator;
	}

	/**
	 * 清除Ttl数据源过滤器
	 * @return 清除Ttl数据源过滤器
	 */
	@Bean
	public ClearTtlDataSourceFilter clearTtlDsFilter() {
		return new ClearTtlDataSourceFilter();
	}

}

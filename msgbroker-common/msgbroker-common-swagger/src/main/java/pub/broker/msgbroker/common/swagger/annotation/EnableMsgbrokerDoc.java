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

package pub.broker.msgbroker.common.swagger.annotation;

import pub.broker.msgbroker.common.core.factory.YamlPropertySourceFactory;
import pub.broker.msgbroker.common.swagger.config.OpenAPIDefinitionImportSelector;
import pub.broker.msgbroker.common.swagger.support.SwaggerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import java.lang.annotation.*;

/**
 * 开启 msgbroker spring doc
 *
 * @author broker.pub
 * @date 2022-03-26
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@EnableConfigurationProperties(SwaggerProperties.class)
@Import(OpenAPIDefinitionImportSelector.class)
@PropertySource(value = "classpath:openapi-config.yaml", factory = YamlPropertySourceFactory.class)
public @interface EnableMsgbrokerDoc {

	/**
	 * 网关路由前缀
	 * @return String
	 */
	String value();

	/**
	 * 是否是微服务架构
	 * @return true
	 */
	boolean isMicro() default true;

}

/*
 * Copyright (c) 2019-2029, Dreamlu 卢春梦 (596392912@qq.com & www.dreamlu.net).
 * <p>
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE 3.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pub.broker.msgbroker.common.xss;

import pub.broker.msgbroker.common.xss.core.DefaultXssCleaner;
import pub.broker.msgbroker.common.xss.core.FormXssClean;
import pub.broker.msgbroker.common.xss.core.JacksonXssClean;
import pub.broker.msgbroker.common.xss.core.XssCleaner;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * jackson xss 配置
 *
 * @author L.cm
 */
@AutoConfiguration
@RequiredArgsConstructor
@EnableConfigurationProperties(pub.broker.msgbroker.common.xss.config.MsgbrokerXssProperties.class)
@ConditionalOnProperty(prefix = pub.broker.msgbroker.common.xss.config.MsgbrokerXssProperties.PREFIX, name = "enabled",
		havingValue = "true", matchIfMissing = true)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class MsgbrokerXssAutoConfiguration implements WebMvcConfigurer {

	private final pub.broker.msgbroker.common.xss.config.MsgbrokerXssProperties xssProperties;

	@Bean
	@ConditionalOnMissingBean
	public XssCleaner xssCleaner(pub.broker.msgbroker.common.xss.config.MsgbrokerXssProperties properties) {
		return new DefaultXssCleaner(properties);
	}

	@Bean
	public FormXssClean formXssClean(pub.broker.msgbroker.common.xss.config.MsgbrokerXssProperties properties,
			XssCleaner xssCleaner) {
		return new FormXssClean(properties, xssCleaner);
	}

	@Bean
	public Jackson2ObjectMapperBuilderCustomizer xssJacksonCustomizer(
			pub.broker.msgbroker.common.xss.config.MsgbrokerXssProperties properties, XssCleaner xssCleaner) {
		return builder -> builder.deserializerByType(String.class, new JacksonXssClean(properties, xssCleaner));
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		List<String> patterns = xssProperties.getPathPatterns();
		if (patterns.isEmpty()) {
			patterns.add("/**");
		}
		pub.broker.msgbroker.common.xss.core.XssCleanInterceptor interceptor = new pub.broker.msgbroker.common.xss.core.XssCleanInterceptor(
				xssProperties);
		registry.addInterceptor(interceptor)
			.addPathPatterns(patterns)
			.excludePathPatterns(xssProperties.getPathExcludePatterns())
			.order(Ordered.LOWEST_PRECEDENCE);
	}

}

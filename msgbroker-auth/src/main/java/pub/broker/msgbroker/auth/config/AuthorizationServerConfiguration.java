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

package pub.broker.msgbroker.auth.config;

import pub.broker.msgbroker.auth.support.CustomeOAuth2AccessTokenGenerator;
import pub.broker.msgbroker.auth.support.core.CustomeOAuth2TokenCustomizer;
import pub.broker.msgbroker.auth.support.core.FormIdentityLoginConfigurer;
import pub.broker.msgbroker.auth.support.core.MsgbrokerDaoAuthenticationProvider;
import pub.broker.msgbroker.auth.support.filter.PasswordDecoderFilter;
import pub.broker.msgbroker.auth.support.filter.ValidateCodeFilter;
import pub.broker.msgbroker.auth.support.handler.MsgbrokerAuthenticationFailureEventHandler;
import pub.broker.msgbroker.auth.support.handler.MsgbrokerAuthenticationSuccessEventHandler;
import pub.broker.msgbroker.auth.support.password.OAuth2ResourceOwnerPasswordAuthenticationConverter;
import pub.broker.msgbroker.auth.support.password.OAuth2ResourceOwnerPasswordAuthenticationProvider;
import pub.broker.msgbroker.auth.support.sms.OAuth2ResourceOwnerSmsAuthenticationConverter;
import pub.broker.msgbroker.auth.support.sms.OAuth2ResourceOwnerSmsAuthenticationProvider;
import pub.broker.msgbroker.common.core.constant.SecurityConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.DelegatingOAuth2TokenGenerator;
import org.springframework.security.oauth2.server.authorization.token.OAuth2RefreshTokenGenerator;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.security.oauth2.server.authorization.web.authentication.*;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Arrays;

/**
 * @author broker.pub
 * @date 2022/5/27
 * <p>
 * 认证服务器配置
 */
@Configuration
@RequiredArgsConstructor
public class AuthorizationServerConfiguration {

	private final OAuth2AuthorizationService authorizationService;

	private final PasswordDecoderFilter passwordDecoderFilter;

	private final ValidateCodeFilter validateCodeFilter;

	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	@ConditionalOnProperty(value = "security.micro", matchIfMissing = true)
	public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
		OAuth2AuthorizationServerConfigurer authorizationServerConfigurer = new OAuth2AuthorizationServerConfigurer();

		// 增加验证码过滤器
		http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class);
		// 增加密码解密过滤器
		http.addFilterBefore(passwordDecoderFilter, UsernamePasswordAuthenticationFilter.class);

		http.with(authorizationServerConfigurer.tokenEndpoint((tokenEndpoint) -> {// 个性化认证授权端点
			tokenEndpoint.accessTokenRequestConverter(accessTokenRequestConverter()) // 注入自定义的授权认证Converter
				.accessTokenResponseHandler(new MsgbrokerAuthenticationSuccessEventHandler()) // 登录成功处理器
				.errorResponseHandler(new MsgbrokerAuthenticationFailureEventHandler());// 登录失败处理器
		}).clientAuthentication(oAuth2ClientAuthenticationConfigurer -> // 个性化客户端认证
		oAuth2ClientAuthenticationConfigurer.errorResponseHandler(new MsgbrokerAuthenticationFailureEventHandler()))// 处理客户端认证异常
			.authorizationEndpoint(authorizationEndpoint -> authorizationEndpoint// 授权码端点个性化confirm页面
				.consentPage(SecurityConstants.CUSTOM_CONSENT_PAGE_URI)), Customizer.withDefaults());

		AntPathRequestMatcher[] requestMatchers = new AntPathRequestMatcher[] {
				AntPathRequestMatcher.antMatcher("/token/**"), AntPathRequestMatcher.antMatcher("/actuator/**"),
				AntPathRequestMatcher.antMatcher("/code/image"), AntPathRequestMatcher.antMatcher("/css/**"),
				AntPathRequestMatcher.antMatcher("/error") };

		http.authorizeHttpRequests(authorizeRequests -> {
			// 自定义接口、端点暴露
			authorizeRequests.requestMatchers(requestMatchers).permitAll();
			authorizeRequests.anyRequest().authenticated();
		})
			.with(authorizationServerConfigurer.authorizationService(authorizationService)// redis存储token的实现
				.authorizationServerSettings(
						AuthorizationServerSettings.builder().issuer(SecurityConstants.PROJECT_LICENSE).build()),
					Customizer.withDefaults());
		http.with(new FormIdentityLoginConfigurer(), Customizer.withDefaults());
		DefaultSecurityFilterChain securityFilterChain = http.build();

		// 注入自定义授权模式实现
		addCustomOAuth2GrantAuthenticationProvider(http);

		return securityFilterChain;
	}

	/**
	 * 令牌生成规则实现 </br>
	 * client:username:uuid
	 * @return OAuth2TokenGenerator
	 */
	@Bean
	public OAuth2TokenGenerator oAuth2TokenGenerator() {
		CustomeOAuth2AccessTokenGenerator accessTokenGenerator = new CustomeOAuth2AccessTokenGenerator();
		// 注入Token 增加关联用户信息
		accessTokenGenerator.setAccessTokenCustomizer(new CustomeOAuth2TokenCustomizer());
		return new DelegatingOAuth2TokenGenerator(accessTokenGenerator, new OAuth2RefreshTokenGenerator());
	}

	/**
	 * request -> xToken 注入请求转换器
	 * @return DelegatingAuthenticationConverter
	 */
	@Bean
	public AuthenticationConverter accessTokenRequestConverter() {
		return new DelegatingAuthenticationConverter(Arrays.asList(
				new OAuth2ResourceOwnerPasswordAuthenticationConverter(),
				new OAuth2ResourceOwnerSmsAuthenticationConverter(), new OAuth2RefreshTokenAuthenticationConverter(),
				new OAuth2ClientCredentialsAuthenticationConverter(),
				new OAuth2AuthorizationCodeAuthenticationConverter(),
				new OAuth2AuthorizationCodeRequestAuthenticationConverter()));
	}

	/**
	 * 注入授权模式实现提供方
	 * <p>
	 * 1. 密码模式 </br>
	 * 2. 短信登录 </br>
	 */
	@SuppressWarnings("unchecked")
	private void addCustomOAuth2GrantAuthenticationProvider(HttpSecurity http) {
		AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
		OAuth2AuthorizationService authorizationService = http.getSharedObject(OAuth2AuthorizationService.class);

		OAuth2ResourceOwnerPasswordAuthenticationProvider resourceOwnerPasswordAuthenticationProvider = new OAuth2ResourceOwnerPasswordAuthenticationProvider(
				authenticationManager, authorizationService, oAuth2TokenGenerator());

		OAuth2ResourceOwnerSmsAuthenticationProvider resourceOwnerSmsAuthenticationProvider = new OAuth2ResourceOwnerSmsAuthenticationProvider(
				authenticationManager, authorizationService, oAuth2TokenGenerator());

		// 处理 UsernamePasswordAuthenticationToken
		http.authenticationProvider(new MsgbrokerDaoAuthenticationProvider());
		// 处理 OAuth2ResourceOwnerPasswordAuthenticationToken
		http.authenticationProvider(resourceOwnerPasswordAuthenticationProvider);
		// 处理 OAuth2ResourceOwnerSmsAuthenticationToken
		http.authenticationProvider(resourceOwnerSmsAuthenticationProvider);
	}

}

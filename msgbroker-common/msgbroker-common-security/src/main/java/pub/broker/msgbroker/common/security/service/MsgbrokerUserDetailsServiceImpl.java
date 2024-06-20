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

package pub.broker.msgbroker.common.security.service;

import pub.broker.msgbroker.admin.api.dto.UserDTO;
import pub.broker.msgbroker.admin.api.dto.UserInfo;
import pub.broker.msgbroker.admin.api.feign.RemoteUserService;
import pub.broker.msgbroker.common.core.constant.CacheConstants;
import pub.broker.msgbroker.common.core.constant.SecurityConstants;
import pub.broker.msgbroker.common.core.util.R;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 用户详细信息
 *
 * @author broker.pub hccake
 */
@Slf4j
@Primary
@RequiredArgsConstructor
public class MsgbrokerUserDetailsServiceImpl implements MsgbrokerUserDetailsService {

	private final RemoteUserService remoteUserService;

	private final CacheManager cacheManager;

	/**
	 * 用户名密码登录
	 * @param username 用户名
	 * @return
	 */
	@Override
	@SneakyThrows
	public UserDetails loadUserByUsername(String username) {
		Cache cache = cacheManager.getCache(CacheConstants.USER_DETAILS);
		if (cache != null && cache.get(username) != null) {
			return (MsgbrokerUser) cache.get(username).get();
		}

		UserDTO userDTO = new UserDTO();
		userDTO.setUsername(username);
		R<UserInfo> result = remoteUserService.info(userDTO, SecurityConstants.FROM_IN);
		UserDetails userDetails = getUserDetails(result);
		if (cache != null) {
			cache.put(username, userDetails);
		}
		return userDetails;
	}

	@Override
	public int getOrder() {
		return Integer.MIN_VALUE;
	}

}

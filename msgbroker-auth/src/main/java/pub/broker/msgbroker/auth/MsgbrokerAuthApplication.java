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

package pub.broker.msgbroker.auth;

import pub.broker.msgbroker.common.feign.annotation.EnableMsgbrokerFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author broker.pub
 * @date 2018年06月21日 认证授权中心
 */
@EnableMsgbrokerFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class MsgbrokerAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsgbrokerAuthApplication.class, args);
	}

}

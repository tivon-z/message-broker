/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the PIG4CLOUD.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */

package pub.broker.msgbroker.codegen;

import pub.broker.msgbroker.common.datasource.annotation.EnableDynamicDataSource;
import pub.broker.msgbroker.common.feign.annotation.EnableMsgbrokerFeignClients;
import pub.broker.msgbroker.common.security.annotation.EnableMsgbrokerResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author broker.pub
 * @date 2018/07/29 代码生成模块
 */
@EnableDynamicDataSource
@EnableMsgbrokerFeignClients
@EnableDiscoveryClient
@EnableMsgbrokerResourceServer
@SpringBootApplication
public class MsgbrokerCodeGenApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsgbrokerCodeGenApplication.class, args);
	}

}

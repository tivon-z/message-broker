/*
 *
 *      Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the PIG4CLOUD.com developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: lengleng (wangiegie@gmail.com)
 *
 */

package pub.broker.msgbroker.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import pub.broker.msgbroker.admin.api.dto.SysLogDTO;
import pub.broker.msgbroker.admin.api.entity.SysLog;

import java.util.List;

/**
 * <p>
 * 日志表 服务类
 * </p>
 *
 * @author broker.pub
 * @since 2017-11-20
 */
public interface SysLogService extends IService<SysLog> {

	/**
	 * 分页查询日志
	 * @param page
	 * @param sysLog
	 * @return
	 */
	Page getLogByPage(Page page, SysLogDTO sysLog);

	/**
	 * 插入日志
	 * @param sysLog 日志对象
	 * @return true/false
	 */
	Boolean saveLog(SysLog sysLog);

	/**
	 * 查询日志列表
	 * @param sysLog 查询条件
	 * @return List<SysLog>
	 */
	List<SysLog> getList(SysLogDTO sysLog);

}

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

package pub.broker.msgbroker.admin.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 日志表
 * </p>
 *
 * @author broker.pub
 * @since 2017-11-20
 */
@Data
@Schema(description = "日志")
public class SysLog implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	@TableId(type = IdType.ASSIGN_ID)
	@Schema(description = "日志编号")
	private Long id;

	/**
	 * 日志类型
	 */
	@NotBlank(message = "日志类型不能为空")
	@Schema(description = "日志类型")
	private String logType;

	/**
	 * 日志标题
	 */
	@NotBlank(message = "日志标题不能为空")
	@Schema(description = "日志标题")
	private String title;

	/**
	 * 创建者
	 */
	@TableField(fill = FieldFill.INSERT)
	@Schema(description = "创建人")
	private String createBy;

	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	@Schema(description = "创建时间")
	private LocalDateTime createTime;

	/**
	 * 更新时间
	 */
	@TableField(fill = FieldFill.UPDATE)
	@Schema(description = "更新时间")
	private LocalDateTime updateTime;

	/**
	 * 操作IP地址
	 */
	@Schema(description = "操作ip地址")
	private String remoteAddr;

	/**
	 * 用户代理
	 */
	@Schema(description = "用户代理")
	private String userAgent;

	/**
	 * 请求URI
	 */
	@Schema(description = "请求uri")
	private String requestUri;

	/**
	 * 操作方式
	 */
	@Schema(description = "操作方式")
	private String method;

	/**
	 * 操作提交的数据
	 */
	@Schema(description = "提交数据")
	private String params;

	/**
	 * 执行时间
	 */
	@Schema(description = "方法执行时间")
	private Long time;

	/**
	 * 异常信息
	 */
	@Schema(description = "异常信息")
	private String exception;

	/**
	 * 服务ID
	 */
	@Schema(description = "应用标识")
	private String serviceId;

	/**
	 * 删除标记
	 */
	@TableLogic
	@TableField(fill = FieldFill.INSERT)
	@Schema(description = "删除标记,1:已删除,0:正常")
	private String delFlag;

}

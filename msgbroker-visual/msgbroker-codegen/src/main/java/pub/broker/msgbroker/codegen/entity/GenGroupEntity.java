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

package pub.broker.msgbroker.codegen.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 模板分组
 *
 * @author PIG
 * @date 2023-02-21 20:01:53
 */
@Data
@TableName("gen_group")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "模板分组")
public class GenGroupEntity extends Model<GenGroupEntity> {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId(type = IdType.ASSIGN_ID)
	@Schema(description = "id")
	private Long id;

	/**
	 * 分组名称
	 */
	@Schema(description = "分组名称")
	private String groupName;

	/**
	 * 分组描述
	 */
	@Schema(description = "分组描述")
	@TableField(fill = FieldFill.INSERT)
	private String groupDesc;

	/**
	 * 创建人
	 */
	@TableField(fill = FieldFill.INSERT)
	@Schema(description = "创建人")
	private String createBy;

	/**
	 * 修改人
	 */
	@TableField(fill = FieldFill.UPDATE)
	@Schema(description = "修改人")
	private String updateBy;

	/**
	 * 创建时间
	 */
	@Schema(description = "创建时间")
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	/**
	 * 修改时间
	 */
	@Schema(description = "修改时间")
	@TableField(fill = FieldFill.UPDATE)
	private LocalDateTime updateTime;

	/**
	 * 删除标识（0-正常,1-删除）
	 */
	@TableLogic
	@TableField(fill = FieldFill.INSERT)
	@Schema(description = "删除标记,1:已删除,0:正常")
	private String delFlag;

}

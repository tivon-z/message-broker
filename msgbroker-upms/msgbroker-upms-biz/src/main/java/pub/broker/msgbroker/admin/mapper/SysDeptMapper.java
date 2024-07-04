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

package pub.broker.msgbroker.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pub.broker.msgbroker.admin.api.entity.SysDept;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 部门管理 Mapper 接口
 * </p>
 *
 * @author broker.pub
 * @since 2018-01-20
 */
@Mapper
public interface SysDeptMapper extends BaseMapper<SysDept> {

}

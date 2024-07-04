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
package pub.broker.msgbroker.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pub.broker.msgbroker.admin.api.entity.SysDict;
import pub.broker.msgbroker.common.core.util.R;

/**
 * 字典表
 *
 * @author broker.pub
 * @date 2019/03/19
 */
public interface SysDictService extends IService<SysDict> {

	/**
	 * 根据ID 删除字典
	 * @param ids ID列表
	 * @return
	 */
	R removeDictByIds(Long[] ids);

	/**
	 * 更新字典
	 * @param sysDict 字典
	 * @return
	 */
	R updateDict(SysDict sysDict);

	/**
	 * 同步缓存 （清空缓存）
	 * @return R
	 */
	R syncDictCache();

}

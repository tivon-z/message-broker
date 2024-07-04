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

package pub.broker.msgbroker.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pub.broker.msgbroker.admin.api.entity.SysOauthClientDetails;
import pub.broker.msgbroker.admin.mapper.SysOauthClientDetailsMapper;
import pub.broker.msgbroker.admin.service.SysOauthClientDetailsService;
import pub.broker.msgbroker.common.core.constant.CacheConstants;
import pub.broker.msgbroker.common.core.util.R;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author broker.pub
 * @since 2018-05-15
 */
@Service
@RequiredArgsConstructor
public class SysOauthClientDetailsServiceImpl extends ServiceImpl<SysOauthClientDetailsMapper, SysOauthClientDetails>
		implements SysOauthClientDetailsService {

	/**
	 * 根据客户端信息
	 * @param clientDetails
	 * @return
	 */
	@Override
	@CacheEvict(value = CacheConstants.CLIENT_DETAILS_KEY, key = "#clientDetails.clientId")
	@Transactional(rollbackFor = Exception.class)
	public Boolean updateClientById(SysOauthClientDetails clientDetails) {
		this.insertOrUpdate(clientDetails);
		return Boolean.TRUE;
	}

	/**
	 * 添加客户端
	 * @param clientDetails
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean saveClient(SysOauthClientDetails clientDetails) {
		this.insertOrUpdate(clientDetails);
		return Boolean.TRUE;
	}

	/**
	 * 插入或更新客户端对象
	 * @param clientDetails
	 * @return
	 */
	private SysOauthClientDetails insertOrUpdate(SysOauthClientDetails clientDetails) {
		// 更新数据库
		saveOrUpdate(clientDetails);
		return clientDetails;
	}

	/**
	 * 分页查询客户端信息
	 * @param page
	 * @param query
	 * @return
	 */
	@Override
	public Page queryPage(Page page, SysOauthClientDetails query) {
		return baseMapper.selectPage(page, Wrappers.query(query));
	}

	@Override
	@CacheEvict(value = CacheConstants.CLIENT_DETAILS_KEY, allEntries = true)
	public R syncClientCache() {
		return R.ok();
	}

}

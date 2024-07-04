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

package pub.broker.msgbroker.admin.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pub.broker.msgbroker.admin.api.entity.SysDict;
import pub.broker.msgbroker.admin.api.entity.SysDictItem;
import pub.broker.msgbroker.admin.service.SysDictItemService;
import pub.broker.msgbroker.admin.service.SysDictService;
import pub.broker.msgbroker.common.core.constant.CacheConstants;
import pub.broker.msgbroker.common.core.util.R;
import pub.broker.msgbroker.common.log.annotation.SysLog;
import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 字典表 前端控制器
 * </p>
 *
 * @author broker.pub
 * @since 2019-03-19
 */
@RestController
@AllArgsConstructor
@RequestMapping("/dict")
@Tag(description = "dict", name = "字典管理模块")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class SysDictController {

	private final SysDictService sysDictService;

	private final SysDictItemService sysDictItemService;

	/**
	 * 通过ID查询字典信息
	 * @param id ID
	 * @return 字典信息
	 */
	@GetMapping("/details/{id}")
	public R getById(@PathVariable Long id) {
		return R.ok(sysDictService.getById(id));
	}

	/**
	 * 查询字典信息
	 * @param query 查询信息
	 * @return 字典信息
	 */
	@GetMapping("/details")
	public R getDetails(@ParameterObject SysDict query) {
		return R.ok(sysDictService.getOne(Wrappers.query(query), false));
	}

	/**
	 * 分页查询字典信息
	 * @param page 分页对象
	 * @return 分页对象
	 */
	@GetMapping("/page")
	public R<IPage> getDictPage(@ParameterObject Page page, @ParameterObject SysDict sysDict) {
		return R.ok(sysDictService.page(page,
				Wrappers.<SysDict>lambdaQuery()
					.eq(StrUtil.isNotBlank(sysDict.getSystemFlag()), SysDict::getSystemFlag, sysDict.getSystemFlag())
					.like(StrUtil.isNotBlank(sysDict.getDictType()), SysDict::getDictType, sysDict.getDictType())));
	}

	/**
	 * 通过字典类型查找字典
	 * @param type 类型
	 * @return 同类型字典
	 */
	@GetMapping("/type/{type}")
	@Cacheable(value = CacheConstants.DICT_DETAILS, key = "#type", unless = "#result.data.isEmpty()")
	public R<List<SysDictItem>> getDictByType(@PathVariable String type) {
		return R.ok(sysDictItemService.list(Wrappers.<SysDictItem>query().lambda().eq(SysDictItem::getDictType, type)));
	}

	/**
	 * 添加字典
	 * @param sysDict 字典信息
	 * @return success、false
	 */
	@SysLog("添加字典")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('sys_dict_add')")
	public R save(@Valid @RequestBody SysDict sysDict) {
		sysDictService.save(sysDict);
		return R.ok(sysDict);
	}

	/**
	 * 删除字典，并且清除字典缓存
	 * @param ids ID
	 * @return R
	 */
	@SysLog("删除字典")
	@DeleteMapping
	@PreAuthorize("@pms.hasPermission('sys_dict_del')")
	@CacheEvict(value = CacheConstants.DICT_DETAILS, allEntries = true)
	public R removeById(@RequestBody Long[] ids) {
		return R.ok(sysDictService.removeDictByIds(ids));
	}

	/**
	 * 修改字典
	 * @param sysDict 字典信息
	 * @return success/false
	 */
	@PutMapping
	@SysLog("修改字典")
	@PreAuthorize("@pms.hasPermission('sys_dict_edit')")
	public R updateById(@Valid @RequestBody SysDict sysDict) {
		return sysDictService.updateDict(sysDict);
	}

	/**
	 * 分页查询
	 * @param name 名称或者字典项
	 * @return
	 */
	@GetMapping("/list")
	public R getDictList(String name) {
		return R.ok(sysDictService.list(Wrappers.<SysDict>lambdaQuery()
			.like(StrUtil.isNotBlank(name), SysDict::getDictType, name)
			.or()
			.like(StrUtil.isNotBlank(name), SysDict::getDescription, name)));
	}

	/**
	 * 分页查询
	 * @param page 分页对象
	 * @param sysDictItem 字典项
	 * @return
	 */
	@GetMapping("/item/page")
	public R getSysDictItemPage(Page page, SysDictItem sysDictItem) {
		return R.ok(sysDictItemService.page(page, Wrappers.query(sysDictItem)));
	}

	/**
	 * 通过id查询字典项
	 * @param id id
	 * @return R
	 */
	@GetMapping("/item/details/{id}")
	public R getDictItemById(@PathVariable("id") Long id) {
		return R.ok(sysDictItemService.getById(id));
	}

	/**
	 * 查询字典项详情
	 * @param query 查询条件
	 * @return R
	 */
	@GetMapping("/item/details")
	public R getDictItemDetails(SysDictItem query) {
		return R.ok(sysDictItemService.getOne(Wrappers.query(query), false));
	}

	/**
	 * 新增字典项
	 * @param sysDictItem 字典项
	 * @return R
	 */
	@SysLog("新增字典项")
	@PostMapping("/item")
	@CacheEvict(value = CacheConstants.DICT_DETAILS, allEntries = true)
	public R save(@RequestBody SysDictItem sysDictItem) {
		return R.ok(sysDictItemService.save(sysDictItem));
	}

	/**
	 * 修改字典项
	 * @param sysDictItem 字典项
	 * @return R
	 */
	@SysLog("修改字典项")
	@PutMapping("/item")
	public R updateById(@RequestBody SysDictItem sysDictItem) {
		return sysDictItemService.updateDictItem(sysDictItem);
	}

	/**
	 * 通过id删除字典项
	 * @param id id
	 * @return R
	 */
	@SysLog("删除字典项")
	@DeleteMapping("/item/{id}")
	public R removeDictItemById(@PathVariable Long id) {
		return sysDictItemService.removeDictItem(id);
	}

	/**
	 * 同步缓存字典
	 * @return R
	 */
	@SysLog("同步字典")
	@PutMapping("/sync")
	public R sync() {
		return sysDictService.syncDictCache();
	}

	@ResponseExcel
	@GetMapping("/export")
	public List<SysDictItem> export(SysDictItem sysDictItem) {
		return sysDictItemService.list(Wrappers.query(sysDictItem));
	}

}

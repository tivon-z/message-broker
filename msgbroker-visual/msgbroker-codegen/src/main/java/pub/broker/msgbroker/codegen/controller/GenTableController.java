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

package pub.broker.msgbroker.codegen.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pub.broker.msgbroker.codegen.entity.GenTable;
import pub.broker.msgbroker.codegen.entity.GenTableColumnEntity;
import pub.broker.msgbroker.codegen.service.GenTableColumnService;
import pub.broker.msgbroker.codegen.service.GenTableService;
import pub.broker.msgbroker.common.core.util.R;
import pub.broker.msgbroker.common.log.annotation.SysLog;
import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 列属性
 *
 * @author msgbrokerx code generator
 * @date 2023-02-06 20:34:55
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/table")
@Tag(description = "table", name = "列属性管理")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class GenTableController {

	private final GenTableColumnService tableColumnService;

	private final GenTableService tableService;

	/**
	 * 分页查询
	 * @param page 分页对象
	 * @param table 列属性
	 * @return
	 */
	@Operation(summary = "分页查询", description = "分页查询")
	@GetMapping("/page")
	public R getTablePage(Page page, GenTable table) {

		return R.ok(tableService.list(page, table));
	}

	/**
	 * 通过id查询列属性
	 * @param id id
	 * @return R
	 */
	@Operation(summary = "通过id查询", description = "通过id查询")
	@GetMapping("/{id}")
	public R getById(@PathVariable("id") Long id) {
		return R.ok(tableService.getById(id));
	}

	/**
	 * 新增列属性
	 * @param table 列属性
	 * @return R
	 */
	@Operation(summary = "新增列属性", description = "新增列属性")
	@SysLog("新增列属性")
	@PostMapping
	public R save(@RequestBody GenTable table) {
		return R.ok(tableService.save(table));
	}

	/**
	 * 修改列属性
	 * @param table 列属性
	 * @return R
	 */
	@Operation(summary = "修改列属性", description = "修改列属性")
	@SysLog("修改列属性")
	@PutMapping
	public R updateById(@RequestBody GenTable table) {
		return R.ok(tableService.updateById(table));
	}

	/**
	 * 通过id删除列属性
	 * @param id id
	 * @return R
	 */
	@Operation(summary = "通过id删除列属性", description = "通过id删除列属性")
	@SysLog("通过id删除列属性")
	@DeleteMapping("/{id}")
	public R removeById(@PathVariable Long id) {
		return R.ok(tableService.removeById(id));
	}

	/**
	 * 导出excel 表格
	 * @param table 查询条件
	 * @return excel 文件流
	 */
	@ResponseExcel
	@GetMapping("/export")
	public List<GenTable> export(GenTable table) {
		return tableService.list(Wrappers.query(table));
	}

	@GetMapping("/list/{dsName}")
	public R listTable(@PathVariable("dsName") String dsName) {
		return R.ok(tableService.queryDsAllTable(dsName));
	}

	@GetMapping("/column/{dsName}/{tableName}")
	public R column(@PathVariable("dsName") String dsName, @PathVariable String tableName) {
		return R.ok(tableService.queryColumn(dsName, tableName));
	}

	/**
	 * 获取表信息
	 * @param dsName 数据源
	 * @param tableName 表名称
	 */
	@GetMapping("/{dsName}/{tableName}")
	public R<GenTable> info(@PathVariable("dsName") String dsName, @PathVariable String tableName) {
		return R.ok(tableService.queryOrBuildTable(dsName, tableName));
	}

	/**
	 * 同步表信息
	 * @param dsName 数据源
	 * @param tableName 表名称
	 */
	@GetMapping("/sync/{dsName}/{tableName}")
	public R<GenTable> sync(@PathVariable("dsName") String dsName, @PathVariable String tableName) {
		// 表配置删除
		tableService.remove(
				Wrappers.<GenTable>lambdaQuery().eq(GenTable::getDsName, dsName).eq(GenTable::getTableName, tableName));
		// 字段配置删除
		tableColumnService.remove(Wrappers.<GenTableColumnEntity>lambdaQuery()
			.eq(GenTableColumnEntity::getDsName, dsName)
			.eq(GenTableColumnEntity::getTableName, tableName));
		return R.ok(tableService.queryOrBuildTable(dsName, tableName));
	}

	/**
	 * 修改表字段数据
	 * @param dsName 数据源
	 * @param tableName 表名称
	 * @param tableFieldList 字段列表
	 */
	@PutMapping("/field/{dsName}/{tableName}")
	public R<String> updateTableField(@PathVariable("dsName") String dsName, @PathVariable String tableName,
			@RequestBody List<GenTableColumnEntity> tableFieldList) {
		tableColumnService.updateTableField(dsName, tableName, tableFieldList);
		return R.ok();
	}

}

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

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.ContentType;
import cn.smallbun.screw.boot.config.Screw;
import cn.smallbun.screw.boot.properties.ScrewProperties;
import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pub.broker.msgbroker.codegen.entity.GenDatasourceConf;
import pub.broker.msgbroker.codegen.service.GenDatasourceConfService;
import pub.broker.msgbroker.common.core.util.R;
import pub.broker.msgbroker.common.core.util.SpringContextHolder;
import pub.broker.msgbroker.common.security.annotation.Inner;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;

/**
 * 数据源管理
 *
 * @author broker.pub
 * @date 2019-03-31 16:00:20
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/dsconf")
public class GenDsConfController {

	private final GenDatasourceConfService datasourceConfService;

	private final Screw screw;

	/**
	 * 分页查询
	 * @param page 分页对象
	 * @param datasourceConf 数据源表
	 * @return
	 */
	@GetMapping("/page")
	public R getSysDatasourceConfPage(Page page, GenDatasourceConf datasourceConf) {
		return R.ok(datasourceConfService.page(page,
				Wrappers.<GenDatasourceConf>lambdaQuery()
					.like(StrUtil.isNotBlank(datasourceConf.getDsName()), GenDatasourceConf::getDsName,
							datasourceConf.getDsName())));
	}

	/**
	 * 查询全部数据源
	 * @return
	 */
	@GetMapping("/list")
	@Inner(value = false)
	public R list() {
		return R.ok(datasourceConfService.list());
	}

	/**
	 * 通过id查询数据源表
	 * @param id id
	 * @return R
	 */
	@GetMapping("/{id}")
	public R getById(@PathVariable("id") Long id) {
		return R.ok(datasourceConfService.getById(id));
	}

	/**
	 * 新增数据源表
	 * @param datasourceConf 数据源表
	 * @return R
	 */
	@PostMapping
	public R save(@RequestBody GenDatasourceConf datasourceConf) {
		return R.ok(datasourceConfService.saveDsByEnc(datasourceConf));
	}

	/**
	 * 修改数据源表
	 * @param conf 数据源表
	 * @return R
	 */
	@PutMapping
	public R updateById(@RequestBody GenDatasourceConf conf) {
		return R.ok(datasourceConfService.updateDsByEnc(conf));
	}

	/**
	 * 通过id删除数据源表
	 * @param ids id
	 * @return R
	 */
	@DeleteMapping
	public R removeById(@RequestBody Long[] ids) {
		return R.ok(datasourceConfService.removeByDsId(ids));
	}

	/**
	 * 查询数据源对应的文档
	 * @param dsName 数据源名称
	 */
	@SneakyThrows
	@GetMapping("/doc")
	public void generatorDoc(String dsName, HttpServletResponse response) {
		// 设置指定的数据源
		DynamicRoutingDataSource dynamicRoutingDataSource = SpringContextHolder.getBean(DynamicRoutingDataSource.class);
		DynamicDataSourceContextHolder.push(dsName);
		DataSource dataSource = dynamicRoutingDataSource.determineDataSource();

		// 设置指定的目标表
		ScrewProperties screwProperties = SpringContextHolder.getBean(ScrewProperties.class);

		// 生成
		byte[] data = screw.documentGeneration(dataSource, screwProperties).toByteArray();
		response.reset();
		response.addHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(data.length));
		response.setContentType(ContentType.OCTET_STREAM.getValue());
		IoUtil.write(response.getOutputStream(), Boolean.TRUE, data);
	}

}

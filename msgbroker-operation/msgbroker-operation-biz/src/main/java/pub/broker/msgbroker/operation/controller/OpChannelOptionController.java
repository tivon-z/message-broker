package pub.broker.msgbroker.operation.controller;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pub.broker.msgbroker.common.core.util.R;
import pub.broker.msgbroker.common.log.annotation.SysLog;
import pub.broker.msgbroker.operation.api.entity.OpChannelOptionEntity;
import pub.broker.msgbroker.operation.service.OpChannelOptionService;
import org.springframework.security.access.prepost.PreAuthorize;
import com.pig4cloud.plugin.excel.annotation.ResponseExcel;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpHeaders;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 渠道的配置项
 *
 * @author msgbroker
 * @date 2024-06-19 14:51:05
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/opChannelOption" )
@Tag(description = "opChannelOption" , name = "渠道的配置项管理" )
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class OpChannelOptionController {

    private final  OpChannelOptionService opChannelOptionService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param opChannelOption 渠道的配置项
     * @return
     */
    @Operation(summary = "分页查询" , description = "分页查询" )
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('operation_opChannelOption_view')" )
    public R getOpChannelOptionPage(@ParameterObject Page page, @ParameterObject OpChannelOptionEntity opChannelOption) {
        LambdaQueryWrapper<OpChannelOptionEntity> wrapper = Wrappers.lambdaQuery();
        return R.ok(opChannelOptionService.page(page, wrapper));
    }


    /**
     * 通过id查询渠道的配置项
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id查询" , description = "通过id查询" )
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('operation_opChannelOption_view')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(opChannelOptionService.getById(id));
    }

    /**
     * 新增渠道的配置项
     * @param opChannelOption 渠道的配置项
     * @return R
     */
    @Operation(summary = "新增渠道的配置项" , description = "新增渠道的配置项" )
    @SysLog("新增渠道的配置项" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('operation_opChannelOption_add')" )
    public R save(@RequestBody OpChannelOptionEntity opChannelOption) {
        return R.ok(opChannelOptionService.save(opChannelOption));
    }

    /**
     * 修改渠道的配置项
     * @param opChannelOption 渠道的配置项
     * @return R
     */
    @Operation(summary = "修改渠道的配置项" , description = "修改渠道的配置项" )
    @SysLog("修改渠道的配置项" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('operation_opChannelOption_edit')" )
    public R updateById(@RequestBody OpChannelOptionEntity opChannelOption) {
        return R.ok(opChannelOptionService.updateById(opChannelOption));
    }

    /**
     * 通过id删除渠道的配置项
     * @param ids id列表
     * @return R
     */
    @Operation(summary = "通过id删除渠道的配置项" , description = "通过id删除渠道的配置项" )
    @SysLog("通过id删除渠道的配置项" )
    @DeleteMapping
    @PreAuthorize("@pms.hasPermission('operation_opChannelOption_del')" )
    public R removeById(@RequestBody Long[] ids) {
        return R.ok(opChannelOptionService.removeBatchByIds(CollUtil.toList(ids)));
    }


    /**
     * 导出excel 表格
     * @param opChannelOption 查询条件
   	 * @param ids 导出指定ID
     * @return excel 文件流
     */
    @ResponseExcel
    @GetMapping("/export")
    @PreAuthorize("@pms.hasPermission('operation_opChannelOption_export')" )
    public List<OpChannelOptionEntity> export(OpChannelOptionEntity opChannelOption,Long[] ids) {
        return opChannelOptionService.list(Wrappers.lambdaQuery(opChannelOption).in(ArrayUtil.isNotEmpty(ids), OpChannelOptionEntity::getId, ids));
    }
}
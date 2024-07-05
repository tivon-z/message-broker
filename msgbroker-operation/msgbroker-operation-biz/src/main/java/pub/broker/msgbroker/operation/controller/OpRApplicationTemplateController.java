package pub.broker.msgbroker.operation.controller;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pub.broker.msgbroker.common.core.util.R;
import pub.broker.msgbroker.common.log.annotation.SysLog;
import pub.broker.msgbroker.operation.api.entity.OpRApplicationTemplateEntity;
import pub.broker.msgbroker.operation.service.OpRApplicationTemplateService;
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
 * 应用与模板关联表
 *
 * @author msgbroker
 * @date 2024-06-19 14:49:08
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/opRApplicationTemplate" )
@Tag(description = "opRApplicationTemplate" , name = "应用与模板关联表管理" )
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class OpRApplicationTemplateController {

    private final  OpRApplicationTemplateService opRApplicationTemplateService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param opRApplicationTemplate 应用与模板关联表
     * @return
     */
    @Operation(summary = "分页查询" , description = "分页查询" )
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('operation_opRApplicationTemplate_view')" )
    public R getOpRApplicationTemplatePage(@ParameterObject Page page, @ParameterObject OpRApplicationTemplateEntity opRApplicationTemplate) {
        LambdaQueryWrapper<OpRApplicationTemplateEntity> wrapper = Wrappers.lambdaQuery();
        return R.ok(opRApplicationTemplateService.page(page, wrapper));
    }


    /**
     * 通过id查询应用与模板关联表
     * @param applicationId id
     * @return R
     */
    @Operation(summary = "通过id查询" , description = "通过id查询" )
    @GetMapping("/{applicationId}" )
    @PreAuthorize("@pms.hasPermission('operation_opRApplicationTemplate_view')" )
    public R getById(@PathVariable("applicationId" ) Long applicationId) {
        return R.ok(opRApplicationTemplateService.getById(applicationId));
    }

    /**
     * 新增应用与模板关联表
     * @param opRApplicationTemplate 应用与模板关联表
     * @return R
     */
    @Operation(summary = "新增应用与模板关联表" , description = "新增应用与模板关联表" )
    @SysLog("新增应用与模板关联表" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('operation_opRApplicationTemplate_add')" )
    public R save(@RequestBody OpRApplicationTemplateEntity opRApplicationTemplate) {
        return R.ok(opRApplicationTemplateService.save(opRApplicationTemplate));
    }

    /**
     * 修改应用与模板关联表
     * @param opRApplicationTemplate 应用与模板关联表
     * @return R
     */
    @Operation(summary = "修改应用与模板关联表" , description = "修改应用与模板关联表" )
    @SysLog("修改应用与模板关联表" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('operation_opRApplicationTemplate_edit')" )
    public R updateById(@RequestBody OpRApplicationTemplateEntity opRApplicationTemplate) {
        return R.ok(opRApplicationTemplateService.updateById(opRApplicationTemplate));
    }

    /**
     * 通过id删除应用与模板关联表
     * @param ids applicationId列表
     * @return R
     */
    @Operation(summary = "通过id删除应用与模板关联表" , description = "通过id删除应用与模板关联表" )
    @SysLog("通过id删除应用与模板关联表" )
    @DeleteMapping
    @PreAuthorize("@pms.hasPermission('operation_opRApplicationTemplate_del')" )
    public R removeById(@RequestBody Long[] ids) {
        return R.ok(opRApplicationTemplateService.removeBatchByIds(CollUtil.toList(ids)));
    }


    /**
     * 导出excel 表格
     * @param opRApplicationTemplate 查询条件
   	 * @param ids 导出指定ID
     * @return excel 文件流
     */
    @ResponseExcel
    @GetMapping("/export")
    @PreAuthorize("@pms.hasPermission('operation_opRApplicationTemplate_export')" )
    public List<OpRApplicationTemplateEntity> export(OpRApplicationTemplateEntity opRApplicationTemplate,Long[] ids) {
        return opRApplicationTemplateService.list(Wrappers.lambdaQuery(opRApplicationTemplate).in(ArrayUtil.isNotEmpty(ids), OpRApplicationTemplateEntity::getApplicationId, ids));
    }
}
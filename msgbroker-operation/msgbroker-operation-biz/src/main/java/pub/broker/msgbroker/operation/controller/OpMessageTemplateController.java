package pub.broker.msgbroker.operation.controller;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pub.broker.msgbroker.common.core.util.R;
import pub.broker.msgbroker.common.log.annotation.SysLog;
import pub.broker.msgbroker.operation.api.entity.OpMessageTemplateEntity;
import pub.broker.msgbroker.operation.service.OpMessageTemplateService;
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
 * 消息模板
 *
 * @author msgbroker
 * @date 2024-06-19 14:49:25
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/opMessageTemplate" )
@Tag(description = "opMessageTemplate" , name = "消息模板管理" )
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class OpMessageTemplateController {

    private final  OpMessageTemplateService opMessageTemplateService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param opMessageTemplate 消息模板
     * @return
     */
    @Operation(summary = "分页查询" , description = "分页查询" )
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('operation_opMessageTemplate_view')" )
    public R getOpMessageTemplatePage(@ParameterObject Page page, @ParameterObject OpMessageTemplateEntity opMessageTemplate) {
        LambdaQueryWrapper<OpMessageTemplateEntity> wrapper = Wrappers.lambdaQuery();
        return R.ok(opMessageTemplateService.page(page, wrapper));
    }


    /**
     * 通过id查询消息模板
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id查询" , description = "通过id查询" )
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('operation_opMessageTemplate_view')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(opMessageTemplateService.getById(id));
    }

    /**
     * 新增消息模板
     * @param opMessageTemplate 消息模板
     * @return R
     */
    @Operation(summary = "新增消息模板" , description = "新增消息模板" )
    @SysLog("新增消息模板" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('operation_opMessageTemplate_add')" )
    public R save(@RequestBody OpMessageTemplateEntity opMessageTemplate) {
        return R.ok(opMessageTemplateService.save(opMessageTemplate));
    }

    /**
     * 修改消息模板
     * @param opMessageTemplate 消息模板
     * @return R
     */
    @Operation(summary = "修改消息模板" , description = "修改消息模板" )
    @SysLog("修改消息模板" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('operation_opMessageTemplate_edit')" )
    public R updateById(@RequestBody OpMessageTemplateEntity opMessageTemplate) {
        return R.ok(opMessageTemplateService.updateById(opMessageTemplate));
    }

    /**
     * 通过id删除消息模板
     * @param ids id列表
     * @return R
     */
    @Operation(summary = "通过id删除消息模板" , description = "通过id删除消息模板" )
    @SysLog("通过id删除消息模板" )
    @DeleteMapping
    @PreAuthorize("@pms.hasPermission('operation_opMessageTemplate_del')" )
    public R removeById(@RequestBody Long[] ids) {
        return R.ok(opMessageTemplateService.removeBatchByIds(CollUtil.toList(ids)));
    }


    /**
     * 导出excel 表格
     * @param opMessageTemplate 查询条件
   	 * @param ids 导出指定ID
     * @return excel 文件流
     */
    @ResponseExcel
    @GetMapping("/export")
    @PreAuthorize("@pms.hasPermission('operation_opMessageTemplate_export')" )
    public List<OpMessageTemplateEntity> export(OpMessageTemplateEntity opMessageTemplate,Long[] ids) {
        return opMessageTemplateService.list(Wrappers.lambdaQuery(opMessageTemplate).in(ArrayUtil.isNotEmpty(ids), OpMessageTemplateEntity::getId, ids));
    }
}
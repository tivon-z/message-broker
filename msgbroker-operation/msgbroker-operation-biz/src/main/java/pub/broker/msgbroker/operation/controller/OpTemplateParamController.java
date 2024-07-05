package pub.broker.msgbroker.operation.controller;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pub.broker.msgbroker.common.core.util.R;
import pub.broker.msgbroker.common.log.annotation.SysLog;
import pub.broker.msgbroker.operation.api.entity.OpTemplateParamEntity;
import pub.broker.msgbroker.operation.service.OpTemplateParamService;
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
 * 消息模板参数
 *
 * @author msgbroker
 * @date 2024-06-19 14:48:55
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/opTemplateParam" )
@Tag(description = "opTemplateParam" , name = "消息模板参数管理" )
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class OpTemplateParamController {

    private final  OpTemplateParamService opTemplateParamService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param opTemplateParam 消息模板参数
     * @return
     */
    @Operation(summary = "分页查询" , description = "分页查询" )
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('operation_opTemplateParam_view')" )
    public R getOpTemplateParamPage(@ParameterObject Page page, @ParameterObject OpTemplateParamEntity opTemplateParam) {
        LambdaQueryWrapper<OpTemplateParamEntity> wrapper = Wrappers.lambdaQuery();
        return R.ok(opTemplateParamService.page(page, wrapper));
    }


    /**
     * 通过id查询消息模板参数
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id查询" , description = "通过id查询" )
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('operation_opTemplateParam_view')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(opTemplateParamService.getById(id));
    }

    /**
     * 新增消息模板参数
     * @param opTemplateParam 消息模板参数
     * @return R
     */
    @Operation(summary = "新增消息模板参数" , description = "新增消息模板参数" )
    @SysLog("新增消息模板参数" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('operation_opTemplateParam_add')" )
    public R save(@RequestBody OpTemplateParamEntity opTemplateParam) {
        return R.ok(opTemplateParamService.save(opTemplateParam));
    }

    /**
     * 修改消息模板参数
     * @param opTemplateParam 消息模板参数
     * @return R
     */
    @Operation(summary = "修改消息模板参数" , description = "修改消息模板参数" )
    @SysLog("修改消息模板参数" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('operation_opTemplateParam_edit')" )
    public R updateById(@RequestBody OpTemplateParamEntity opTemplateParam) {
        return R.ok(opTemplateParamService.updateById(opTemplateParam));
    }

    /**
     * 通过id删除消息模板参数
     * @param ids id列表
     * @return R
     */
    @Operation(summary = "通过id删除消息模板参数" , description = "通过id删除消息模板参数" )
    @SysLog("通过id删除消息模板参数" )
    @DeleteMapping
    @PreAuthorize("@pms.hasPermission('operation_opTemplateParam_del')" )
    public R removeById(@RequestBody Long[] ids) {
        return R.ok(opTemplateParamService.removeBatchByIds(CollUtil.toList(ids)));
    }


    /**
     * 导出excel 表格
     * @param opTemplateParam 查询条件
   	 * @param ids 导出指定ID
     * @return excel 文件流
     */
    @ResponseExcel
    @GetMapping("/export")
    @PreAuthorize("@pms.hasPermission('operation_opTemplateParam_export')" )
    public List<OpTemplateParamEntity> export(OpTemplateParamEntity opTemplateParam,Long[] ids) {
        return opTemplateParamService.list(Wrappers.lambdaQuery(opTemplateParam).in(ArrayUtil.isNotEmpty(ids), OpTemplateParamEntity::getId, ids));
    }
}
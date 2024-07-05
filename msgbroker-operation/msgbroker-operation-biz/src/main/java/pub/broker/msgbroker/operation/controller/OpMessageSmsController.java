package pub.broker.msgbroker.operation.controller;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pub.broker.msgbroker.common.core.util.R;
import pub.broker.msgbroker.common.log.annotation.SysLog;
import pub.broker.msgbroker.operation.api.entity.OpMessageSmsEntity;
import pub.broker.msgbroker.operation.service.OpMessageSmsService;
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
 * sms消息
 *
 * @author msgbroker
 * @date 2024-06-19 14:34:21
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/opMessageSms" )
@Tag(description = "opMessageSms" , name = "sms消息管理" )
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class OpMessageSmsController {

    private final  OpMessageSmsService opMessageSmsService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param opMessageSms sms消息
     * @return
     */
    @Operation(summary = "分页查询" , description = "分页查询" )
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('operation_opMessageSms_view')" )
    public R getOpMessageSmsPage(@ParameterObject Page page, @ParameterObject OpMessageSmsEntity opMessageSms) {
        LambdaQueryWrapper<OpMessageSmsEntity> wrapper = Wrappers.lambdaQuery();
        return R.ok(opMessageSmsService.page(page, wrapper));
    }


    /**
     * 通过id查询sms消息
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id查询" , description = "通过id查询" )
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('operation_opMessageSms_view')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(opMessageSmsService.getById(id));
    }

    /**
     * 新增sms消息
     * @param opMessageSms sms消息
     * @return R
     */
    @Operation(summary = "新增sms消息" , description = "新增sms消息" )
    @SysLog("新增sms消息" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('operation_opMessageSms_add')" )
    public R save(@RequestBody OpMessageSmsEntity opMessageSms) {
        return R.ok(opMessageSmsService.save(opMessageSms));
    }

    /**
     * 修改sms消息
     * @param opMessageSms sms消息
     * @return R
     */
    @Operation(summary = "修改sms消息" , description = "修改sms消息" )
    @SysLog("修改sms消息" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('operation_opMessageSms_edit')" )
    public R updateById(@RequestBody OpMessageSmsEntity opMessageSms) {
        return R.ok(opMessageSmsService.updateById(opMessageSms));
    }

    /**
     * 通过id删除sms消息
     * @param ids id列表
     * @return R
     */
    @Operation(summary = "通过id删除sms消息" , description = "通过id删除sms消息" )
    @SysLog("通过id删除sms消息" )
    @DeleteMapping
    @PreAuthorize("@pms.hasPermission('operation_opMessageSms_del')" )
    public R removeById(@RequestBody Long[] ids) {
        return R.ok(opMessageSmsService.removeBatchByIds(CollUtil.toList(ids)));
    }


    /**
     * 导出excel 表格
     * @param opMessageSms 查询条件
   	 * @param ids 导出指定ID
     * @return excel 文件流
     */
    @ResponseExcel
    @GetMapping("/export")
    @PreAuthorize("@pms.hasPermission('operation_opMessageSms_export')" )
    public List<OpMessageSmsEntity> export(OpMessageSmsEntity opMessageSms,Long[] ids) {
        return opMessageSmsService.list(Wrappers.lambdaQuery(opMessageSms).in(ArrayUtil.isNotEmpty(ids), OpMessageSmsEntity::getId, ids));
    }
}
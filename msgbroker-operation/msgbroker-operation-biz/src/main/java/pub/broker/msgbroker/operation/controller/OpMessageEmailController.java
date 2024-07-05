package pub.broker.msgbroker.operation.controller;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pub.broker.msgbroker.common.core.util.R;
import pub.broker.msgbroker.common.log.annotation.SysLog;
import pub.broker.msgbroker.operation.api.entity.OpMessageEmailEntity;
import pub.broker.msgbroker.operation.service.OpMessageEmailService;
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
 * email消息
 *
 * @author msgbroker
 * @date 2024-06-19 14:34:47
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/opMessageEmail" )
@Tag(description = "opMessageEmail" , name = "email消息管理" )
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class OpMessageEmailController {

    private final  OpMessageEmailService opMessageEmailService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param opMessageEmail email消息
     * @return
     */
    @Operation(summary = "分页查询" , description = "分页查询" )
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('operation_opMessageEmail_view')" )
    public R getOpMessageEmailPage(@ParameterObject Page page, @ParameterObject OpMessageEmailEntity opMessageEmail) {
        LambdaQueryWrapper<OpMessageEmailEntity> wrapper = Wrappers.lambdaQuery();
        return R.ok(opMessageEmailService.page(page, wrapper));
    }


    /**
     * 通过id查询email消息
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id查询" , description = "通过id查询" )
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('operation_opMessageEmail_view')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(opMessageEmailService.getById(id));
    }

    /**
     * 新增email消息
     * @param opMessageEmail email消息
     * @return R
     */
    @Operation(summary = "新增email消息" , description = "新增email消息" )
    @SysLog("新增email消息" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('operation_opMessageEmail_add')" )
    public R save(@RequestBody OpMessageEmailEntity opMessageEmail) {
        return R.ok(opMessageEmailService.save(opMessageEmail));
    }

    /**
     * 修改email消息
     * @param opMessageEmail email消息
     * @return R
     */
    @Operation(summary = "修改email消息" , description = "修改email消息" )
    @SysLog("修改email消息" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('operation_opMessageEmail_edit')" )
    public R updateById(@RequestBody OpMessageEmailEntity opMessageEmail) {
        return R.ok(opMessageEmailService.updateById(opMessageEmail));
    }

    /**
     * 通过id删除email消息
     * @param ids id列表
     * @return R
     */
    @Operation(summary = "通过id删除email消息" , description = "通过id删除email消息" )
    @SysLog("通过id删除email消息" )
    @DeleteMapping
    @PreAuthorize("@pms.hasPermission('operation_opMessageEmail_del')" )
    public R removeById(@RequestBody Long[] ids) {
        return R.ok(opMessageEmailService.removeBatchByIds(CollUtil.toList(ids)));
    }


    /**
     * 导出excel 表格
     * @param opMessageEmail 查询条件
   	 * @param ids 导出指定ID
     * @return excel 文件流
     */
    @ResponseExcel
    @GetMapping("/export")
    @PreAuthorize("@pms.hasPermission('operation_opMessageEmail_export')" )
    public List<OpMessageEmailEntity> export(OpMessageEmailEntity opMessageEmail,Long[] ids) {
        return opMessageEmailService.list(Wrappers.lambdaQuery(opMessageEmail).in(ArrayUtil.isNotEmpty(ids), OpMessageEmailEntity::getId, ids));
    }
}
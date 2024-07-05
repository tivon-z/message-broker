package pub.broker.msgbroker.operation.controller;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pub.broker.msgbroker.common.core.util.R;
import pub.broker.msgbroker.common.log.annotation.SysLog;
import pub.broker.msgbroker.operation.api.entity.OpMessageWebhookEntity;
import pub.broker.msgbroker.operation.service.OpMessageWebhookService;
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
 * webhook消息
 *
 * @author msgbroker
 * @date 2024-06-19 14:33:31
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/opMessageWebhook" )
@Tag(description = "opMessageWebhook" , name = "webhook消息管理" )
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class OpMessageWebhookController {

    private final  OpMessageWebhookService opMessageWebhookService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param opMessageWebhook webhook消息
     * @return
     */
    @Operation(summary = "分页查询" , description = "分页查询" )
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('operation_opMessageWebhook_view')" )
    public R getOpMessageWebhookPage(@ParameterObject Page page, @ParameterObject OpMessageWebhookEntity opMessageWebhook) {
        LambdaQueryWrapper<OpMessageWebhookEntity> wrapper = Wrappers.lambdaQuery();
        return R.ok(opMessageWebhookService.page(page, wrapper));
    }


    /**
     * 通过id查询webhook消息
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id查询" , description = "通过id查询" )
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('operation_opMessageWebhook_view')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(opMessageWebhookService.getById(id));
    }

    /**
     * 新增webhook消息
     * @param opMessageWebhook webhook消息
     * @return R
     */
    @Operation(summary = "新增webhook消息" , description = "新增webhook消息" )
    @SysLog("新增webhook消息" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('operation_opMessageWebhook_add')" )
    public R save(@RequestBody OpMessageWebhookEntity opMessageWebhook) {
        return R.ok(opMessageWebhookService.save(opMessageWebhook));
    }

    /**
     * 修改webhook消息
     * @param opMessageWebhook webhook消息
     * @return R
     */
    @Operation(summary = "修改webhook消息" , description = "修改webhook消息" )
    @SysLog("修改webhook消息" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('operation_opMessageWebhook_edit')" )
    public R updateById(@RequestBody OpMessageWebhookEntity opMessageWebhook) {
        return R.ok(opMessageWebhookService.updateById(opMessageWebhook));
    }

    /**
     * 通过id删除webhook消息
     * @param ids id列表
     * @return R
     */
    @Operation(summary = "通过id删除webhook消息" , description = "通过id删除webhook消息" )
    @SysLog("通过id删除webhook消息" )
    @DeleteMapping
    @PreAuthorize("@pms.hasPermission('operation_opMessageWebhook_del')" )
    public R removeById(@RequestBody Long[] ids) {
        return R.ok(opMessageWebhookService.removeBatchByIds(CollUtil.toList(ids)));
    }


    /**
     * 导出excel 表格
     * @param opMessageWebhook 查询条件
   	 * @param ids 导出指定ID
     * @return excel 文件流
     */
    @ResponseExcel
    @GetMapping("/export")
    @PreAuthorize("@pms.hasPermission('operation_opMessageWebhook_export')" )
    public List<OpMessageWebhookEntity> export(OpMessageWebhookEntity opMessageWebhook,Long[] ids) {
        return opMessageWebhookService.list(Wrappers.lambdaQuery(opMessageWebhook).in(ArrayUtil.isNotEmpty(ids), OpMessageWebhookEntity::getId, ids));
    }
}
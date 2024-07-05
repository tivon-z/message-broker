package pub.broker.msgbroker.operation.controller;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pub.broker.msgbroker.common.core.util.R;
import pub.broker.msgbroker.common.log.annotation.SysLog;
import pub.broker.msgbroker.operation.api.entity.OpMessageEntity;
import pub.broker.msgbroker.operation.service.OpMessageService;
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
 * 消息
 *
 * @author msgbroker
 * @date 2024-06-19 14:35:08
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/opMessage" )
@Tag(description = "opMessage" , name = "消息管理" )
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class OpMessageController {

    private final  OpMessageService opMessageService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param opMessage 消息
     * @return
     */
    @Operation(summary = "分页查询" , description = "分页查询" )
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('operation_opMessage_view')" )
    public R getOpMessagePage(@ParameterObject Page page, @ParameterObject OpMessageEntity opMessage) {
        LambdaQueryWrapper<OpMessageEntity> wrapper = Wrappers.lambdaQuery();
        return R.ok(opMessageService.page(page, wrapper));
    }


    /**
     * 通过id查询消息
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id查询" , description = "通过id查询" )
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('operation_opMessage_view')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(opMessageService.getById(id));
    }

    /**
     * 新增消息
     * @param opMessage 消息
     * @return R
     */
    @Operation(summary = "新增消息" , description = "新增消息" )
    @SysLog("新增消息" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('operation_opMessage_add')" )
    public R save(@RequestBody OpMessageEntity opMessage) {
        return R.ok(opMessageService.save(opMessage));
    }

    /**
     * 修改消息
     * @param opMessage 消息
     * @return R
     */
    @Operation(summary = "修改消息" , description = "修改消息" )
    @SysLog("修改消息" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('operation_opMessage_edit')" )
    public R updateById(@RequestBody OpMessageEntity opMessage) {
        return R.ok(opMessageService.updateById(opMessage));
    }

    /**
     * 通过id删除消息
     * @param ids id列表
     * @return R
     */
    @Operation(summary = "通过id删除消息" , description = "通过id删除消息" )
    @SysLog("通过id删除消息" )
    @DeleteMapping
    @PreAuthorize("@pms.hasPermission('operation_opMessage_del')" )
    public R removeById(@RequestBody Long[] ids) {
        return R.ok(opMessageService.removeBatchByIds(CollUtil.toList(ids)));
    }


    /**
     * 导出excel 表格
     * @param opMessage 查询条件
   	 * @param ids 导出指定ID
     * @return excel 文件流
     */
    @ResponseExcel
    @GetMapping("/export")
    @PreAuthorize("@pms.hasPermission('operation_opMessage_export')" )
    public List<OpMessageEntity> export(OpMessageEntity opMessage,Long[] ids) {
        return opMessageService.list(Wrappers.lambdaQuery(opMessage).in(ArrayUtil.isNotEmpty(ids), OpMessageEntity::getId, ids));
    }
}
package pub.broker.msgbroker.operation.controller;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pub.broker.msgbroker.common.core.util.R;
import pub.broker.msgbroker.common.log.annotation.SysLog;
import pub.broker.msgbroker.operation.api.entity.OpMessagePushplusEntity;
import pub.broker.msgbroker.operation.service.OpMessagePushplusService;
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
 * pushplus消息
 *
 * @author msgbroker
 * @date 2024-06-19 14:34:08
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/opMessagePushplus" )
@Tag(description = "opMessagePushplus" , name = "pushplus消息管理" )
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class OpMessagePushplusController {

    private final  OpMessagePushplusService opMessagePushplusService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param opMessagePushplus pushplus消息
     * @return
     */
    @Operation(summary = "分页查询" , description = "分页查询" )
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('operation_opMessagePushplus_view')" )
    public R getOpMessagePushplusPage(@ParameterObject Page page, @ParameterObject OpMessagePushplusEntity opMessagePushplus) {
        LambdaQueryWrapper<OpMessagePushplusEntity> wrapper = Wrappers.lambdaQuery();
        return R.ok(opMessagePushplusService.page(page, wrapper));
    }


    /**
     * 通过id查询pushplus消息
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id查询" , description = "通过id查询" )
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('operation_opMessagePushplus_view')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(opMessagePushplusService.getById(id));
    }

    /**
     * 新增pushplus消息
     * @param opMessagePushplus pushplus消息
     * @return R
     */
    @Operation(summary = "新增pushplus消息" , description = "新增pushplus消息" )
    @SysLog("新增pushplus消息" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('operation_opMessagePushplus_add')" )
    public R save(@RequestBody OpMessagePushplusEntity opMessagePushplus) {
        return R.ok(opMessagePushplusService.save(opMessagePushplus));
    }

    /**
     * 修改pushplus消息
     * @param opMessagePushplus pushplus消息
     * @return R
     */
    @Operation(summary = "修改pushplus消息" , description = "修改pushplus消息" )
    @SysLog("修改pushplus消息" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('operation_opMessagePushplus_edit')" )
    public R updateById(@RequestBody OpMessagePushplusEntity opMessagePushplus) {
        return R.ok(opMessagePushplusService.updateById(opMessagePushplus));
    }

    /**
     * 通过id删除pushplus消息
     * @param ids id列表
     * @return R
     */
    @Operation(summary = "通过id删除pushplus消息" , description = "通过id删除pushplus消息" )
    @SysLog("通过id删除pushplus消息" )
    @DeleteMapping
    @PreAuthorize("@pms.hasPermission('operation_opMessagePushplus_del')" )
    public R removeById(@RequestBody Long[] ids) {
        return R.ok(opMessagePushplusService.removeBatchByIds(CollUtil.toList(ids)));
    }


    /**
     * 导出excel 表格
     * @param opMessagePushplus 查询条件
   	 * @param ids 导出指定ID
     * @return excel 文件流
     */
    @ResponseExcel
    @GetMapping("/export")
    @PreAuthorize("@pms.hasPermission('operation_opMessagePushplus_export')" )
    public List<OpMessagePushplusEntity> export(OpMessagePushplusEntity opMessagePushplus,Long[] ids) {
        return opMessagePushplusService.list(Wrappers.lambdaQuery(opMessagePushplus).in(ArrayUtil.isNotEmpty(ids), OpMessagePushplusEntity::getId, ids));
    }
}
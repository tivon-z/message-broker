package pub.broker.msgbroker.operation.controller;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pub.broker.msgbroker.common.core.util.R;
import pub.broker.msgbroker.common.log.annotation.SysLog;
import pub.broker.msgbroker.operation.api.entity.OpMessageServerchanEntity;
import pub.broker.msgbroker.operation.service.OpMessageServerchanService;
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
 * serverchan消息
 *
 * @author msgbroker
 * @date 2024-06-19 14:33:54
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/opMessageServerchan" )
@Tag(description = "opMessageServerchan" , name = "serverchan消息管理" )
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class OpMessageServerchanController {

    private final  OpMessageServerchanService opMessageServerchanService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param opMessageServerchan serverchan消息
     * @return
     */
    @Operation(summary = "分页查询" , description = "分页查询" )
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('operation_opMessageServerchan_view')" )
    public R getOpMessageServerchanPage(@ParameterObject Page page, @ParameterObject OpMessageServerchanEntity opMessageServerchan) {
        LambdaQueryWrapper<OpMessageServerchanEntity> wrapper = Wrappers.lambdaQuery();
        return R.ok(opMessageServerchanService.page(page, wrapper));
    }


    /**
     * 通过id查询serverchan消息
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id查询" , description = "通过id查询" )
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('operation_opMessageServerchan_view')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(opMessageServerchanService.getById(id));
    }

    /**
     * 新增serverchan消息
     * @param opMessageServerchan serverchan消息
     * @return R
     */
    @Operation(summary = "新增serverchan消息" , description = "新增serverchan消息" )
    @SysLog("新增serverchan消息" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('operation_opMessageServerchan_add')" )
    public R save(@RequestBody OpMessageServerchanEntity opMessageServerchan) {
        return R.ok(opMessageServerchanService.save(opMessageServerchan));
    }

    /**
     * 修改serverchan消息
     * @param opMessageServerchan serverchan消息
     * @return R
     */
    @Operation(summary = "修改serverchan消息" , description = "修改serverchan消息" )
    @SysLog("修改serverchan消息" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('operation_opMessageServerchan_edit')" )
    public R updateById(@RequestBody OpMessageServerchanEntity opMessageServerchan) {
        return R.ok(opMessageServerchanService.updateById(opMessageServerchan));
    }

    /**
     * 通过id删除serverchan消息
     * @param ids id列表
     * @return R
     */
    @Operation(summary = "通过id删除serverchan消息" , description = "通过id删除serverchan消息" )
    @SysLog("通过id删除serverchan消息" )
    @DeleteMapping
    @PreAuthorize("@pms.hasPermission('operation_opMessageServerchan_del')" )
    public R removeById(@RequestBody Long[] ids) {
        return R.ok(opMessageServerchanService.removeBatchByIds(CollUtil.toList(ids)));
    }


    /**
     * 导出excel 表格
     * @param opMessageServerchan 查询条件
   	 * @param ids 导出指定ID
     * @return excel 文件流
     */
    @ResponseExcel
    @GetMapping("/export")
    @PreAuthorize("@pms.hasPermission('operation_opMessageServerchan_export')" )
    public List<OpMessageServerchanEntity> export(OpMessageServerchanEntity opMessageServerchan,Long[] ids) {
        return opMessageServerchanService.list(Wrappers.lambdaQuery(opMessageServerchan).in(ArrayUtil.isNotEmpty(ids), OpMessageServerchanEntity::getId, ids));
    }
}
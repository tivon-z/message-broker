package pub.broker.msgbroker.operation.controller;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pub.broker.msgbroker.common.core.util.R;
import pub.broker.msgbroker.common.log.annotation.SysLog;
import pub.broker.msgbroker.operation.api.entity.OpMessageHisEntity;
import pub.broker.msgbroker.operation.service.OpMessageHisService;
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
 * 消息历史
 *
 * @author msgbroker
 * @date 2024-06-19 14:34:34
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/opMessageHis" )
@Tag(description = "opMessageHis" , name = "消息历史管理" )
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class OpMessageHisController {

    private final  OpMessageHisService opMessageHisService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param opMessageHis 消息历史
     * @return
     */
    @Operation(summary = "分页查询" , description = "分页查询" )
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('operation_opMessageHis_view')" )
    public R getOpMessageHisPage(@ParameterObject Page page, @ParameterObject OpMessageHisEntity opMessageHis) {
        LambdaQueryWrapper<OpMessageHisEntity> wrapper = Wrappers.lambdaQuery();
        return R.ok(opMessageHisService.page(page, wrapper));
    }


    /**
     * 通过id查询消息历史
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id查询" , description = "通过id查询" )
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('operation_opMessageHis_view')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(opMessageHisService.getById(id));
    }

    /**
     * 新增消息历史
     * @param opMessageHis 消息历史
     * @return R
     */
    @Operation(summary = "新增消息历史" , description = "新增消息历史" )
    @SysLog("新增消息历史" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('operation_opMessageHis_add')" )
    public R save(@RequestBody OpMessageHisEntity opMessageHis) {
        return R.ok(opMessageHisService.save(opMessageHis));
    }

    /**
     * 修改消息历史
     * @param opMessageHis 消息历史
     * @return R
     */
    @Operation(summary = "修改消息历史" , description = "修改消息历史" )
    @SysLog("修改消息历史" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('operation_opMessageHis_edit')" )
    public R updateById(@RequestBody OpMessageHisEntity opMessageHis) {
        return R.ok(opMessageHisService.updateById(opMessageHis));
    }

    /**
     * 通过id删除消息历史
     * @param ids id列表
     * @return R
     */
    @Operation(summary = "通过id删除消息历史" , description = "通过id删除消息历史" )
    @SysLog("通过id删除消息历史" )
    @DeleteMapping
    @PreAuthorize("@pms.hasPermission('operation_opMessageHis_del')" )
    public R removeById(@RequestBody Long[] ids) {
        return R.ok(opMessageHisService.removeBatchByIds(CollUtil.toList(ids)));
    }


    /**
     * 导出excel 表格
     * @param opMessageHis 查询条件
   	 * @param ids 导出指定ID
     * @return excel 文件流
     */
    @ResponseExcel
    @GetMapping("/export")
    @PreAuthorize("@pms.hasPermission('operation_opMessageHis_export')" )
    public List<OpMessageHisEntity> export(OpMessageHisEntity opMessageHis,Long[] ids) {
        return opMessageHisService.list(Wrappers.lambdaQuery(opMessageHis).in(ArrayUtil.isNotEmpty(ids), OpMessageHisEntity::getId, ids));
    }
}
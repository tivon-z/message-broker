package pub.broker.msgbroker.operation.controller;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pub.broker.msgbroker.common.core.util.R;
import pub.broker.msgbroker.common.log.annotation.SysLog;
import pub.broker.msgbroker.operation.api.entity.OpDistributionChannelEntity;
import pub.broker.msgbroker.operation.service.OpDistributionChannelService;
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
 * 消息下发渠道
 *
 * @author msgbroker
 * @date 2024-06-19 14:49:38
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/opDistributionChannel" )
@Tag(description = "opDistributionChannel" , name = "消息下发渠道管理" )
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class OpDistributionChannelController {

    private final  OpDistributionChannelService opDistributionChannelService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param opDistributionChannel 消息下发渠道
     * @return
     */
    @Operation(summary = "分页查询" , description = "分页查询" )
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('operation_opDistributionChannel_view')" )
    public R getOpDistributionChannelPage(@ParameterObject Page page, @ParameterObject OpDistributionChannelEntity opDistributionChannel) {
        LambdaQueryWrapper<OpDistributionChannelEntity> wrapper = Wrappers.lambdaQuery();
        return R.ok(opDistributionChannelService.page(page, wrapper));
    }


    /**
     * 通过id查询消息下发渠道
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id查询" , description = "通过id查询" )
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('operation_opDistributionChannel_view')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(opDistributionChannelService.getById(id));
    }

    /**
     * 新增消息下发渠道
     * @param opDistributionChannel 消息下发渠道
     * @return R
     */
    @Operation(summary = "新增消息下发渠道" , description = "新增消息下发渠道" )
    @SysLog("新增消息下发渠道" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('operation_opDistributionChannel_add')" )
    public R save(@RequestBody OpDistributionChannelEntity opDistributionChannel) {
        return R.ok(opDistributionChannelService.save(opDistributionChannel));
    }

    /**
     * 修改消息下发渠道
     * @param opDistributionChannel 消息下发渠道
     * @return R
     */
    @Operation(summary = "修改消息下发渠道" , description = "修改消息下发渠道" )
    @SysLog("修改消息下发渠道" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('operation_opDistributionChannel_edit')" )
    public R updateById(@RequestBody OpDistributionChannelEntity opDistributionChannel) {
        return R.ok(opDistributionChannelService.updateById(opDistributionChannel));
    }

    /**
     * 通过id删除消息下发渠道
     * @param ids id列表
     * @return R
     */
    @Operation(summary = "通过id删除消息下发渠道" , description = "通过id删除消息下发渠道" )
    @SysLog("通过id删除消息下发渠道" )
    @DeleteMapping
    @PreAuthorize("@pms.hasPermission('operation_opDistributionChannel_del')" )
    public R removeById(@RequestBody Long[] ids) {
        return R.ok(opDistributionChannelService.removeBatchByIds(CollUtil.toList(ids)));
    }


    /**
     * 导出excel 表格
     * @param opDistributionChannel 查询条件
   	 * @param ids 导出指定ID
     * @return excel 文件流
     */
    @ResponseExcel
    @GetMapping("/export")
    @PreAuthorize("@pms.hasPermission('operation_opDistributionChannel_export')" )
    public List<OpDistributionChannelEntity> export(OpDistributionChannelEntity opDistributionChannel,Long[] ids) {
        return opDistributionChannelService.list(Wrappers.lambdaQuery(opDistributionChannel).in(ArrayUtil.isNotEmpty(ids), OpDistributionChannelEntity::getId, ids));
    }
}
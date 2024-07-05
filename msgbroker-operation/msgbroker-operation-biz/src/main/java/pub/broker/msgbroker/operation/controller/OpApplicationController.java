package pub.broker.msgbroker.operation.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pub.broker.msgbroker.common.core.util.R;
import pub.broker.msgbroker.common.log.annotation.SysLog;
import pub.broker.msgbroker.common.security.util.SecurityUtils;
import pub.broker.msgbroker.operation.api.entity.OpApplicationEntity;
import pub.broker.msgbroker.operation.api.util.AKUtil;
import pub.broker.msgbroker.operation.service.OpApplicationService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * 应用管理
 *
 * @author msgbroker
 * @date 2024-06-19 14:51:20
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/opApplication" )
@Tag(description = "opApplication" , name = "应用管理管理" )
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class OpApplicationController {

    private final  OpApplicationService opApplicationService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param opApplication 应用管理
     * @return
     */
    @Operation(summary = "分页查询" , description = "分页查询" )
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('operation_opApplication_view')" )
    public R getOpApplicationPage(@ParameterObject Page page, @ParameterObject OpApplicationEntity opApplication) {
        LambdaQueryWrapper<OpApplicationEntity> wrapper = Wrappers.lambdaQuery();
        return R.ok(opApplicationService.page(page, wrapper));
    }


    /**
     * 通过id查询应用管理
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id查询" , description = "通过id查询" )
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('operation_opApplication_view')" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(opApplicationService.getById(id));
    }

    /**
     * 新增应用管理
     * @param opApplication 应用管理
     * @return R
     */
    @Operation(summary = "新增应用管理" , description = "新增应用管理" )
    @SysLog("新增应用管理" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('operation_opApplication_add')" )
    public R save(@RequestBody OpApplicationEntity opApplication) {
		if (opApplication.getExpireTime() == null) {
			opApplication.setExpireTime(LocalDateTime.of(2099, 12, 31, 0, 0));
		}
		opApplication.setAccessKey(RandomUtil.randomString(32));
		opApplication.setUserId(SecurityUtils.getUser().getId());
		opApplicationService.save(opApplication);
		if (Objects.equals(opApplication.getStatus(), 1)) {
			if (LocalDateTime.now().isAfter(opApplication.getExpireTime())) {
				long expire = LocalDateTimeUtil.between(LocalDateTime.now(), opApplication.getExpireTime(), ChronoUnit.SECONDS);
				AKUtil.setAKValid(opApplication.getAccessKey(), expire);
				AKUtil.setAKDayLimit(opApplication.getAccessKey(), 100);
			}
		}
        return R.ok();
    }

    /**
     * 修改应用管理
     * @param opApplication 应用管理
     * @return R
     */
    @Operation(summary = "修改应用管理" , description = "修改应用管理" )
    @SysLog("修改应用管理" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('operation_opApplication_edit')" )
    public R updateById(@RequestBody OpApplicationEntity opApplication) {
		opApplicationService.updateById(opApplication);
		if (Objects.equals(opApplication.getStatus(), 1)) {
			if (LocalDateTime.now().isAfter(opApplication.getExpireTime())) {
				long expire = LocalDateTimeUtil.between(LocalDateTime.now(), opApplication.getExpireTime(), ChronoUnit.SECONDS);
				AKUtil.setAKValid(opApplication.getAccessKey(), expire);
				AKUtil.setAKDayLimit(opApplication.getAccessKey(), 100);
				return R.ok();
			}
		}
		AKUtil.setAKValid(opApplication.getAccessKey(), 1);
		AKUtil.setAKDayLimit(opApplication.getAccessKey(), 0);
		return R.ok();
	}

    /**
     * 通过id删除应用管理
     * @param ids id列表
     * @return R
     */
    @Operation(summary = "通过id删除应用管理" , description = "通过id删除应用管理" )
    @SysLog("通过id删除应用管理" )
    @DeleteMapping
    @PreAuthorize("@pms.hasPermission('operation_opApplication_del')" )
    public R removeById(@RequestBody Long[] ids) {
        return R.ok(opApplicationService.removeBatchByIds(CollUtil.toList(ids)));
    }

}
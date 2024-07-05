package pub.broker.msgbroker.operation.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

/**
 * 消息下发渠道
 *
 * @author msgbroker
 * @date 2024-06-19 14:49:38
 */
@Data
@TableName("op_distribution_channel")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "消息下发渠道")
public class OpDistributionChannelEntity extends Model<OpDistributionChannelEntity> {


	/**
	* ID
	*/
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description="ID")
    private Long id;

	/**
	* 渠道编码
	*/
    @Schema(description="渠道编码")
    private String code;

	/**
	* 渠道名称
	*/
    @Schema(description="渠道名称")
    private String name;

	/**
	* 渠道类型，SMS/EMAIL/pushplus/ServerChan
	*/
    @Schema(description="渠道类型，SMS/EMAIL/pushplus/ServerChan")
    private String type;

	/**
	* 供应商，
	*/
    @Schema(description="供应商，")
    private String provider;

	/**
	* 所属用户ID
	*/
    @Schema(description="所属用户ID")
    private Long userId;

	/**
	* 状态，0禁用，1启用
	*/
    @Schema(description="状态，0禁用，1启用")
    private Integer status;

	/**
	* 备注
	*/
    @Schema(description="备注")
    private String remark;

	/**
	* 创建时间
	*/
	@TableField(fill = FieldFill.INSERT)
    @Schema(description="创建时间")
    private LocalDateTime createTime;

	/**
	* 修改时间
	*/
	@TableField(fill = FieldFill.INSERT_UPDATE)
    @Schema(description="修改时间")
    private LocalDateTime updateTime;
}
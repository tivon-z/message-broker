package pub.broker.msgbroker.operation.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

/**
 * 消息模板
 *
 * @author msgbroker
 * @date 2024-06-19 14:49:25
 */
@Data
@TableName("op_message_template")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "消息模板")
public class OpMessageTemplateEntity extends Model<OpMessageTemplateEntity> {


	/**
	* ID
	*/
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description="ID")
    private Long id;

	/**
	* 模板编码
	*/
    @Schema(description="模板编码")
    private String code;

	/**
	* 模板名称
	*/
    @Schema(description="模板名称")
    private String name;

	/**
	* 所属用户ID
	*/
    @Schema(description="所属用户ID")
    private Long userId;

	/**
	* 模板使用渠道ID
	*/
    @Schema(description="模板使用渠道ID")
    private Long channelId;

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

	/**
	* 删除标志
	*/
    @TableLogic
		@TableField(fill = FieldFill.INSERT)
    @Schema(description="删除标志")
    private String delFlag;
}
package pub.broker.msgbroker.operation.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

/**
 * 应用管理
 *
 * @author msgbroker
 * @date 2024-06-19 14:51:20
 */
@Data
@TableName("op_application")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "应用管理")
public class OpApplicationEntity extends Model<OpApplicationEntity> {


	/**
	* ID
	*/
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description="ID")
    private Long id;

	/**
	* 应用编码
	*/
    @Schema(description="应用编码")
    private String code;

	/**
	* 应用名称
	*/
    @Schema(description="应用名称")
    private String name;

	/**
	* 所属用户ID
	*/
    @Schema(description="所属用户ID")
    private Long userId;

	/**
	* 应用访问key
	*/
    @Schema(description="应用访问key")
    private String accessKey;

	/**
	* 失效时间
	*/
    @Schema(description="失效时间")
    private LocalDateTime expireTime;

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
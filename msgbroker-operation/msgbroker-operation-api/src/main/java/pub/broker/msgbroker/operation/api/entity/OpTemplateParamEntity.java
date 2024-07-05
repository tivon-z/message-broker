package pub.broker.msgbroker.operation.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 消息模板参数
 *
 * @author msgbroker
 * @date 2024-06-19 14:48:55
 */
@Data
@TableName("op_template_param")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "消息模板参数")
public class OpTemplateParamEntity extends Model<OpTemplateParamEntity> {


	/**
	* ID
	*/
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description="ID")
    private Long id;

	/**
	* 模板ID
	*/
    @Schema(description="模板ID")
    private Long templateId;

	/**
	* 模板参数
	*/
    @Schema(description="模板参数")
    private String param;

	/**
	* 模板参数值
	*/
    @Schema(description="模板参数值")
    private String value;
}
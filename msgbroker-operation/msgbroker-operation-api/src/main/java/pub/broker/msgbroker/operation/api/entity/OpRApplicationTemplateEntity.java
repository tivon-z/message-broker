package pub.broker.msgbroker.operation.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 应用与模板关联表
 *
 * @author msgbroker
 * @date 2024-06-19 14:49:08
 */
@Data
@TableName("op_r_application_template")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "应用与模板关联表")
public class OpRApplicationTemplateEntity extends Model<OpRApplicationTemplateEntity> {


	/**
	* 应用ID
	*/
    @Schema(description="应用ID")
    private Long applicationId;

	/**
	* 模板ID
	*/
    @Schema(description="模板ID")
    private Long templateId;
}
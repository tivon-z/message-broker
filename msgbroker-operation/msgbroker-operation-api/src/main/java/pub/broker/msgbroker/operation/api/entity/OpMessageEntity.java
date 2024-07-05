package pub.broker.msgbroker.operation.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

/**
 * 消息
 *
 * @author msgbroker
 * @date 2024-06-19 14:35:08
 */
@Data
@TableName("op_message")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "消息")
public class OpMessageEntity extends Model<OpMessageEntity> {


	/**
	* 主键ID
	*/
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description="主键ID")
    private Long id;

	/**
	* 消息ID
	*/
    @Schema(description="消息ID")
    private String messageId;

	/**
	* 应用访问key
	*/
    @Schema(description="应用访问key")
    private String accessKey;

	/**
	* 模板编码
	*/
    @Schema(description="模板编码")
    private String templateCode;

	/**
	* 接收方
	*/
    @Schema(description="接收方")
    private String to;

	/**
	* 参数表
	*/
    @Schema(description="参数表")
    private String params;

	/**
	* 标题
	*/
    @Schema(description="标题")
    private String title;

	/**
	* 正文
	*/
    @Schema(description="正文")
    private String content;

	/**
	* 附加数据
	*/
    @Schema(description="附加数据")
    private String extra;

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
package pub.broker.msgbroker.operation.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

/**
 * webhook消息
 *
 * @author msgbroker
 * @date 2024-06-19 14:33:31
 */
@Data
@TableName("op_message_webhook")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "webhook消息")
public class OpMessageWebhookEntity extends Model<OpMessageWebhookEntity> {


	/**
	* ID
	*/
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description="ID")
    private Long id;

	/**
	* 消息ID
	*/
    @Schema(description="消息ID")
    private String messageId;

	/**
	* 模板编码
	*/
    @Schema(description="模板编码")
    private String templateCode;

	/**
	* 令牌
	*/
    @Schema(description="令牌")
    private String accessKey;

	/**
	* 标题
	*/
    @Schema(description="标题")
    private String title;

	/**
	* 消息内容
	*/
    @Schema(description="消息内容")
    private String content;

	/**
	* 状态, 0待发送，1成功，2发送中，3失败
	*/
    @Schema(description="状态, 0待发送，1成功，2发送中，3失败")
    private Integer state;

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
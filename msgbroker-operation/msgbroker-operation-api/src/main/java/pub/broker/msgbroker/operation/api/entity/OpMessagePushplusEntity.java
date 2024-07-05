package pub.broker.msgbroker.operation.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

/**
 * pushplus消息
 *
 * @author msgbroker
 * @date 2024-06-19 14:34:08
 */
@Data
@TableName("op_message_pushplus")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "pushplus消息")
public class OpMessagePushplusEntity extends Model<OpMessagePushplusEntity> {


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
	* 用户令牌
	*/
    @Schema(description="用户令牌")
    private String token;

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
	* pushplus消息模板，默认html
	*/
    @Schema(description="pushplus消息模板，默认html")
    private String template;

	/**
	* pushplus消息群组编码
	*/
    @Schema(description="pushplus消息群组编码")
    private String topic;

	/**
	* pushplus发送渠道，默认wechat
	*/
    @Schema(description="pushplus发送渠道，默认wechat")
    private String channel;

	/**
	* webhook编码
	*/
    @Schema(description="webhook编码")
    private String webhook;

	/**
	* 回调地址，异步回调发送结果
	*/
    @Schema(description="回调地址，异步回调发送结果")
    private String callbackUrl;

	/**
	* 时间戳，毫秒。如小于当前时间，消息将无法发送
	*/
    @Schema(description="时间戳，毫秒。如小于当前时间，消息将无法发送")
    private Long timestamp;

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
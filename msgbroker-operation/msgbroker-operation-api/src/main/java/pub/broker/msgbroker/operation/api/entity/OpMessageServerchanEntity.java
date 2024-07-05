package pub.broker.msgbroker.operation.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

/**
 * serverchan消息
 *
 * @author msgbroker
 * @date 2024-06-19 14:33:54
 */
@Data
@TableName("op_message_serverchan")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "serverchan消息")
public class OpMessageServerchanEntity extends Model<OpMessageServerchanEntity> {


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
    private String sendKey;

	/**
	* 标题
	*/
    @Schema(description="标题")
    private String title;

	/**
	* 消息内容
	*/
    @Schema(description="消息内容")
    private String desp;

	/**
	* 消息卡片内容
	*/
    @Schema(description="消息卡片内容")
    private String shortContent;

	/**
	* 是否隐藏调用IP
	*/
    @Schema(description="是否隐藏调用IP")
    private Integer noip;

	/**
	* 发送渠道
	*/
    @Schema(description="发送渠道")
    private String channel;

	/**
	* 消息抄送的openid
	*/
    @Schema(description="消息抄送的openid")
    private String openId;

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
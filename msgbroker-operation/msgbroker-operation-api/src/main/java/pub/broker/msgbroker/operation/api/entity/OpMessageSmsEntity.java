package pub.broker.msgbroker.operation.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

/**
 * sms消息
 *
 * @author msgbroker
 * @date 2024-06-19 14:34:21
 */
@Data
@TableName("op_message_sms")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "sms消息")
public class OpMessageSmsEntity extends Model<OpMessageSmsEntity> {


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
	* 短信服务商的模板编码
	*/
    @Schema(description="短信服务商的模板编码")
    private String smsCode;

	/**
	* 签名
	*/
    @Schema(description="签名")
    private String signName;

	/**
	* 接收人
	*/
    @Schema(description="接收人")
    private String receivers;

	/**
	* 参数
	*/
    @Schema(description="参数")
    private String params;

	/**
	* 内容，自定义短信内容才有
	*/
    @Schema(description="内容，自定义短信内容才有")
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
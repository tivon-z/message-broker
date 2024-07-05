package pub.broker.msgbroker.operation.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

/**
 * email消息
 *
 * @author msgbroker
 * @date 2024-06-19 14:34:47
 */
@Data
@TableName("op_message_email")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "email消息")
public class OpMessageEmailEntity extends Model<OpMessageEmailEntity> {


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
	* 收件人
	*/
    @Schema(description="收件人")
    private String recipients;

	/**
	* 抄送
	*/
    @Schema(description="抄送")
    private String cc;

	/**
	* 主题
	*/
    @Schema(description="主题")
    private String subject;

	/**
	* 正文
	*/
    @Schema(description="正文")
    private String content;

	/**
	* 是否是html
	*/
    @Schema(description="是否是html")
    private Integer htmlFlag;

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
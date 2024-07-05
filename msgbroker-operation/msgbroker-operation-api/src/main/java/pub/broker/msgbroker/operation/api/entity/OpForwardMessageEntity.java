package pub.broker.msgbroker.operation.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

/**
 * 转发消息
 *
 * @author msgbroker
 * @date 2024-06-19 14:48:44
 */
@Data
@TableName("op_forward_message")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "转发消息")
public class OpForwardMessageEntity extends Model<OpForwardMessageEntity> {


	/**
	* ID
	*/
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description="ID")
    private Long id;

	/**
	* 应用访问key
	*/
    @Schema(description="应用访问key")
    private String accessKey;

	/**
	* 类型
	*/
    @Schema(description="类型")
    private String type;

	/**
	* 发送方
	*/
    @Schema(description="发送方")
    private String sender;

	/**
	* 接收方
	*/
    @Schema(description="接收方")
    private String receiver;

	/**
	* 发生时间
	*/
    @Schema(description="发生时间")
    private LocalDateTime happenTime;

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
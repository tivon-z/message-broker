package pub.broker.msgbroker.operation.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 渠道的配置项
 *
 * @author msgbroker
 * @date 2024-06-19 14:51:05
 */
@Data
@TableName("op_channel_option")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "渠道的配置项")
public class OpChannelOptionEntity extends Model<OpChannelOptionEntity> {


	/**
	* ID
	*/
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description="ID")
    private Long id;

	/**
	* 渠道ID
	*/
    @Schema(description="渠道ID")
    private Long channelId;

	/**
	* 配置项
	*/
    @Schema(description="配置项")
    private String option;

	/**
	* 配置项值
	*/
    @Schema(description="配置项值")
    private String value;
}
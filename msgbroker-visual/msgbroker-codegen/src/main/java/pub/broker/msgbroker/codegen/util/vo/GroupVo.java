package pub.broker.msgbroker.codegen.util.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import pub.broker.msgbroker.codegen.entity.GenTemplateEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class GroupVo {

	/**
	 * id
	 */
	@TableId(type = IdType.ASSIGN_ID)
	@Schema(description = "id")
	private Long id;

	/**
	 * 分组名称
	 */
	@Schema(description = "分组名称")
	private String groupName;

	/**
	 * 分组描述
	 */
	@Schema(description = "分组描述")
	private String groupDesc;

	/**
	 * 模板ids
	 */
	@Schema(description = "拥有的模板列表")
	private Long[] templateId;

	/**
	 * 模板列表
	 */
	@Schema(description = "拥有的模板列表")
	private List<GenTemplateEntity> templateList;

}

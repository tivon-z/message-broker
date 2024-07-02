package pub.broker.msgbroker.codegen.util.vo;

import lombok.Data;

/**
 * @author broker.pub
 * @date 2022/5/2
 */
@Data
public class SqlDto {

	/**
	 * 数据源ID
	 */
	private String dsName;

	/**
	 * sql脚本
	 */
	private String sql;

}

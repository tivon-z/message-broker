package pub.broker.msgbroker.common.log.event;

import pub.broker.msgbroker.admin.api.entity.SysLog;
import lombok.Data;

/**
 * spring event log
 *
 * @author broker.pub
 * @date 2023/8/11
 */
@Data
public class SysLogEventSource extends SysLog {

	/**
	 * 参数重写成object
	 */
	private Object body;

}

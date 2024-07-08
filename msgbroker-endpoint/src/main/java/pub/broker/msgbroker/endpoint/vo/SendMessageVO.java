package pub.broker.msgbroker.endpoint.vo;

import lombok.Data;

import java.util.Map;

/**
 * 发送消息结构
 *
 */
@Data
public class SendMessageVO extends MessageVO{

	private String templateCode;  // 模板编码

	private String to;  // 接收方

	private Map<String, Object> params;  // 参数
}

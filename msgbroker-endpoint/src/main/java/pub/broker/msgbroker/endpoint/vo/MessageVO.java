package pub.broker.msgbroker.endpoint.vo;

import lombok.Data;

import java.util.Map;

/**
 * 发送消息结构
 *
 */
@Data
public class MessageVO {

	private String title;  // 标题/主题

	private String content;  // 正文

	private Map<String, Object> extra;  // 附加信息
}

package pub.broker.msgbroker.endpoint.vo;

import lombok.Data;

/**
 * 转发消息结构，sim卡接收到的短信转发到微信等场景
 *
 */
@Data
public class ForwardMessageVO extends SendMessageVO{

	private String type = "SMS";  // 消息类型，SMS,EMAIL,APP

	private String sender;  // 发送方

	private String receiver;  // 接收方

	private Long happenTime;  // 发生时间
}

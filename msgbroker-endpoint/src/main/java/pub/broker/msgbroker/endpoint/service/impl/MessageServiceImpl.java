package pub.broker.msgbroker.endpoint.service.impl;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.json.JSONUtil;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pub.broker.msgbroker.endpoint.service.MessageService;
import pub.broker.msgbroker.endpoint.vo.ForwardMessageVO;
import pub.broker.msgbroker.endpoint.vo.SendMessageVO;
import pub.broker.msgbroker.operation.api.entity.OpForwardMessageEntity;
import pub.broker.msgbroker.operation.api.feign.RemoteForwardMessageService;

@Log4j2
@AllArgsConstructor
@Service
public class MessageServiceImpl implements MessageService {

	private final RemoteForwardMessageService remoteForwardMessageService;

	/**
	 * 发送消息
	 * @param ak
	 * @param msg
	 * @return
	 */
	@Override
	public Boolean send(String ak, SendMessageVO msg){

		return Boolean.TRUE;
	}

	@Override
	public Boolean forward(String ak, ForwardMessageVO msg) {
		OpForwardMessageEntity fm = new OpForwardMessageEntity();
		fm.setAccessKey(ak);
		fm.setType(msg.getType());
		fm.setSender(msg.getSender());
		fm.setReceiver(msg.getReceiver());
		fm.setTitle(msg.getTitle());
		fm.setContent(msg.getContent());
		fm.setHappenTime(LocalDateTimeUtil.of(msg.getHappenTime()));
		fm.setExtra(JSONUtil.toJsonStr(msg.getExtra()));
		remoteForwardMessageService.forward(fm);
		return Boolean.TRUE;
	}
}

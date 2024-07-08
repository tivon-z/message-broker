package pub.broker.msgbroker.endpoint.service;

import pub.broker.msgbroker.endpoint.vo.ForwardMessageVO;
import pub.broker.msgbroker.endpoint.vo.SendMessageVO;

public interface MessageService {


	Boolean send(String ak, SendMessageVO msg);

	Boolean forward(String ak, ForwardMessageVO msg);
}

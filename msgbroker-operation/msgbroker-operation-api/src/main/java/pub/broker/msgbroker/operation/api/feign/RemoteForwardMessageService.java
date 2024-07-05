package pub.broker.msgbroker.operation.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pub.broker.msgbroker.common.core.constant.ServiceNameConstants;
import pub.broker.msgbroker.common.core.util.R;
import pub.broker.msgbroker.operation.api.entity.OpForwardMessageEntity;

/**
 * @author broker.pub
 * @date 2018/6/22
 */
@FeignClient(contextId = "remoteForwardMessageService", value = ServiceNameConstants.OPERATION_SERVICE)
public interface RemoteForwardMessageService {

	@PostMapping("/opForwardMessage/inner/forward")
	R<Boolean> forward(@RequestBody OpForwardMessageEntity opForwardMessage);
}

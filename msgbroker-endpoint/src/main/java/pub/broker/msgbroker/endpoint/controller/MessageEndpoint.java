package pub.broker.msgbroker.endpoint.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;
import pub.broker.msgbroker.common.core.util.R;

import pub.broker.msgbroker.common.security.annotation.Inner;
import pub.broker.msgbroker.endpoint.service.MessageService;
import pub.broker.msgbroker.endpoint.vo.ForwardMessageVO;
import pub.broker.msgbroker.endpoint.vo.SendMessageVO;
import pub.broker.msgbroker.operation.api.util.AKUtil;

/**
 * message send
 *
 */
@Inner(false)
@Log4j2
@RestController
@RequiredArgsConstructor
public class MessageEndpoint {

	private final MessageService messageService;

	@Operation(summary = "send", description = "send")
	@PostMapping("/send")
	public R sendFromPost(@RequestParam("ak") String ak, @RequestBody SendMessageVO msg) {
		log.info("send msg, ak: {}", ak);
		if (!AKUtil.isAKValid(ak)){
			return R.failed("invalid access key.");
		}
		return R.ok(messageService.send(ak, msg));
	}

	@Operation(summary = "send", description = "send")
	@GetMapping("/send")
	public R sendFromGet(@RequestParam("ak") String ak, @ParameterObject SendMessageVO msg) {
		log.info("send msg, ak: {}", ak);
		if (!AKUtil.isAKValid(ak)){
			return R.failed("invalid access key.");
		}
		return R.ok(messageService.send(ak, msg));
	}

	@Operation(summary = "forward", description = "forward")
	@PostMapping("/forward")
	public R forwardFromPost(@RequestParam("ak") String ak, @RequestBody ForwardMessageVO msg) {
		log.info("forward msg, ak: {}", ak);
		if (!AKUtil.isAKValid(ak)){
			return R.failed("invalid access key.");
		}
		return R.ok(messageService.forward(ak, msg));
	}

	@Operation(summary = "forward", description = "forward")
	@GetMapping("/forward")
	public R forwardFromGet(@RequestParam("ak") String ak, @ParameterObject ForwardMessageVO msg) {
		log.info("forward msg, ak: {}", ak);
		if (!AKUtil.isAKValid(ak)){
			return R.failed("invalid access key.");
		}
		return R.ok(messageService.forward(ak, msg));
	}
}

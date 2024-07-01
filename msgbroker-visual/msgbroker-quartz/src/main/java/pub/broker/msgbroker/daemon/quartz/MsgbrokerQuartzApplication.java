package pub.broker.msgbroker.daemon.quartz;

import pub.broker.msgbroker.common.feign.annotation.EnableMsgbrokerFeignClients;
import pub.broker.msgbroker.common.security.annotation.EnableMsgbrokerResourceServer;
import pub.broker.msgbroker.common.swagger.annotation.EnableMsgbrokerDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author frwcloud
 * @date 2023-07-05
 */
@EnableMsgbrokerDoc("job")
@EnableMsgbrokerFeignClients
@EnableMsgbrokerResourceServer
@EnableDiscoveryClient
@SpringBootApplication
public class MsgbrokerQuartzApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsgbrokerQuartzApplication.class, args);
	}

}

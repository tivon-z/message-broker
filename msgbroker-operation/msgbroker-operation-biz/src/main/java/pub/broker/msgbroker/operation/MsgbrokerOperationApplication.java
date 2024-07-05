package pub.broker.msgbroker.operation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import pub.broker.msgbroker.common.feign.annotation.EnableMsgbrokerFeignClients;
import pub.broker.msgbroker.common.security.annotation.EnableMsgbrokerResourceServer;
import pub.broker.msgbroker.common.swagger.annotation.EnableMsgbrokerDoc;


@EnableMsgbrokerDoc(value = "operation")
@EnableMsgbrokerFeignClients
@EnableMsgbrokerResourceServer
@EnableDiscoveryClient
@SpringBootApplication
public class MsgbrokerOperationApplication {
    public static void main(String[] args) {
		SpringApplication.run(MsgbrokerOperationApplication.class, args);
    }
}

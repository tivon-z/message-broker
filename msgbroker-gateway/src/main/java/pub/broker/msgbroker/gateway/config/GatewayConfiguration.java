package pub.broker.msgbroker.gateway.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import pub.broker.msgbroker.gateway.filter.MsgbrokerRequestGlobalFilter;
import pub.broker.msgbroker.gateway.handler.GlobalExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 网关配置
 *
 * @author L.cm
 */
@Configuration(proxyBeanMethods = false)
public class GatewayConfiguration {

	/**
	 * 创建MsgbrokerRequest全局过滤器
	 * @return MsgbrokerRequest全局过滤器
	 */
	@Bean
	public MsgbrokerRequestGlobalFilter msgbrokerRequestGlobalFilter() {
		return new MsgbrokerRequestGlobalFilter();
	}

	/**
	 * 创建全局异常处理程序
	 * @param objectMapper 对象映射器
	 * @return 全局异常处理程序
	 */
	@Bean
	public GlobalExceptionHandler globalExceptionHandler(ObjectMapper objectMapper) {
		return new GlobalExceptionHandler(objectMapper);
	}

}

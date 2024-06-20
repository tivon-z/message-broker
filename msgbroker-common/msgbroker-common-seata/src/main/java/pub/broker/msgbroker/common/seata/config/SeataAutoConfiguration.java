package pub.broker.msgbroker.common.seata.config;

import pub.broker.msgbroker.common.core.factory.YamlPropertySourceFactory;
import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Seata 配置类
 *
 * @author broker.pub
 * @date 2022/3/29
 */
@PropertySource(value = "classpath:seata-config.yml", factory = YamlPropertySourceFactory.class)
@EnableAutoDataSourceProxy(useJdkProxy = true)
@Configuration(proxyBeanMethods = false)
public class SeataAutoConfiguration {

}

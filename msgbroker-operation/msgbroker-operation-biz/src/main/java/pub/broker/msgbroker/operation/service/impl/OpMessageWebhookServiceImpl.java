package pub.broker.msgbroker.operation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pub.broker.msgbroker.operation.api.entity.OpMessageWebhookEntity;
import pub.broker.msgbroker.operation.mapper.OpMessageWebhookMapper;
import pub.broker.msgbroker.operation.service.OpMessageWebhookService;
import org.springframework.stereotype.Service;
/**
 * webhook消息
 *
 * @author msgbroker
 * @date 2024-06-19 14:33:31
 */
@Service
public class OpMessageWebhookServiceImpl extends ServiceImpl<OpMessageWebhookMapper, OpMessageWebhookEntity> implements OpMessageWebhookService {
}
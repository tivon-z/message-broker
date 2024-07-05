package pub.broker.msgbroker.operation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pub.broker.msgbroker.operation.api.entity.OpMessagePushplusEntity;
import pub.broker.msgbroker.operation.mapper.OpMessagePushplusMapper;
import pub.broker.msgbroker.operation.service.OpMessagePushplusService;
import org.springframework.stereotype.Service;
/**
 * pushplus消息
 *
 * @author msgbroker
 * @date 2024-06-19 14:34:08
 */
@Service
public class OpMessagePushplusServiceImpl extends ServiceImpl<OpMessagePushplusMapper, OpMessagePushplusEntity> implements OpMessagePushplusService {
}
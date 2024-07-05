package pub.broker.msgbroker.operation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pub.broker.msgbroker.operation.api.entity.OpMessageSmsEntity;
import pub.broker.msgbroker.operation.mapper.OpMessageSmsMapper;
import pub.broker.msgbroker.operation.service.OpMessageSmsService;
import org.springframework.stereotype.Service;
/**
 * sms消息
 *
 * @author msgbroker
 * @date 2024-06-19 14:34:21
 */
@Service
public class OpMessageSmsServiceImpl extends ServiceImpl<OpMessageSmsMapper, OpMessageSmsEntity> implements OpMessageSmsService {
}
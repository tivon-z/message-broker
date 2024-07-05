package pub.broker.msgbroker.operation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pub.broker.msgbroker.operation.api.entity.OpMessageEntity;
import pub.broker.msgbroker.operation.mapper.OpMessageMapper;
import pub.broker.msgbroker.operation.service.OpMessageService;
import org.springframework.stereotype.Service;
/**
 * 消息
 *
 * @author msgbroker
 * @date 2024-06-19 14:35:08
 */
@Service
public class OpMessageServiceImpl extends ServiceImpl<OpMessageMapper, OpMessageEntity> implements OpMessageService {
}
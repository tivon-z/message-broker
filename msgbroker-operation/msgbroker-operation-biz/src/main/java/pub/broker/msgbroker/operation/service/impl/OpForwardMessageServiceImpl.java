package pub.broker.msgbroker.operation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pub.broker.msgbroker.operation.api.entity.OpForwardMessageEntity;
import pub.broker.msgbroker.operation.mapper.OpForwardMessageMapper;
import pub.broker.msgbroker.operation.service.OpForwardMessageService;
import org.springframework.stereotype.Service;
/**
 * 转发消息
 *
 * @author msgbroker
 * @date 2024-06-19 14:48:44
 */
@Service
public class OpForwardMessageServiceImpl extends ServiceImpl<OpForwardMessageMapper, OpForwardMessageEntity> implements OpForwardMessageService {
}
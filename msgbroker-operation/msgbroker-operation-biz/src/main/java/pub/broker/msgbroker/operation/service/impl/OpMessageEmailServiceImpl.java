package pub.broker.msgbroker.operation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pub.broker.msgbroker.operation.api.entity.OpMessageEmailEntity;
import pub.broker.msgbroker.operation.mapper.OpMessageEmailMapper;
import pub.broker.msgbroker.operation.service.OpMessageEmailService;
import org.springframework.stereotype.Service;
/**
 * email消息
 *
 * @author msgbroker
 * @date 2024-06-19 14:34:47
 */
@Service
public class OpMessageEmailServiceImpl extends ServiceImpl<OpMessageEmailMapper, OpMessageEmailEntity> implements OpMessageEmailService {
}
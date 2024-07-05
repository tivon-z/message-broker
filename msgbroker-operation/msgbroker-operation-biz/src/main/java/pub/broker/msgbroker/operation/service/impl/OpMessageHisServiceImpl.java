package pub.broker.msgbroker.operation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pub.broker.msgbroker.operation.api.entity.OpMessageHisEntity;
import pub.broker.msgbroker.operation.mapper.OpMessageHisMapper;
import pub.broker.msgbroker.operation.service.OpMessageHisService;
import org.springframework.stereotype.Service;
/**
 * 消息历史
 *
 * @author msgbroker
 * @date 2024-06-19 14:34:34
 */
@Service
public class OpMessageHisServiceImpl extends ServiceImpl<OpMessageHisMapper, OpMessageHisEntity> implements OpMessageHisService {
}
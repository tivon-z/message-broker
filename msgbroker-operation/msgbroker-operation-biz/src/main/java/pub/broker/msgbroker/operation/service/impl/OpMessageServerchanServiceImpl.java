package pub.broker.msgbroker.operation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pub.broker.msgbroker.operation.api.entity.OpMessageServerchanEntity;
import pub.broker.msgbroker.operation.mapper.OpMessageServerchanMapper;
import pub.broker.msgbroker.operation.service.OpMessageServerchanService;
import org.springframework.stereotype.Service;
/**
 * serverchan消息
 *
 * @author msgbroker
 * @date 2024-06-19 14:33:54
 */
@Service
public class OpMessageServerchanServiceImpl extends ServiceImpl<OpMessageServerchanMapper, OpMessageServerchanEntity> implements OpMessageServerchanService {
}
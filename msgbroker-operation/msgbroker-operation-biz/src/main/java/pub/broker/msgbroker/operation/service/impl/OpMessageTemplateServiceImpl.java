package pub.broker.msgbroker.operation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pub.broker.msgbroker.operation.api.entity.OpMessageTemplateEntity;
import pub.broker.msgbroker.operation.mapper.OpMessageTemplateMapper;
import pub.broker.msgbroker.operation.service.OpMessageTemplateService;
import org.springframework.stereotype.Service;
/**
 * 消息模板
 *
 * @author msgbroker
 * @date 2024-06-19 14:49:25
 */
@Service
public class OpMessageTemplateServiceImpl extends ServiceImpl<OpMessageTemplateMapper, OpMessageTemplateEntity> implements OpMessageTemplateService {
}
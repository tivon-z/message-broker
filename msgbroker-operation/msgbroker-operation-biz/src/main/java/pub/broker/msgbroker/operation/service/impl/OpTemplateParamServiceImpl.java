package pub.broker.msgbroker.operation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pub.broker.msgbroker.operation.api.entity.OpTemplateParamEntity;
import pub.broker.msgbroker.operation.mapper.OpTemplateParamMapper;
import pub.broker.msgbroker.operation.service.OpTemplateParamService;
import org.springframework.stereotype.Service;
/**
 * 消息模板参数
 *
 * @author msgbroker
 * @date 2024-06-19 14:48:55
 */
@Service
public class OpTemplateParamServiceImpl extends ServiceImpl<OpTemplateParamMapper, OpTemplateParamEntity> implements OpTemplateParamService {
}
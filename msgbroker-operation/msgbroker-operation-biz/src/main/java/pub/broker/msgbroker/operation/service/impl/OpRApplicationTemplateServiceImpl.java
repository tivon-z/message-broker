package pub.broker.msgbroker.operation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pub.broker.msgbroker.operation.api.entity.OpRApplicationTemplateEntity;
import pub.broker.msgbroker.operation.mapper.OpRApplicationTemplateMapper;
import pub.broker.msgbroker.operation.service.OpRApplicationTemplateService;
import org.springframework.stereotype.Service;
/**
 * 应用与模板关联表
 *
 * @author msgbroker
 * @date 2024-06-19 14:49:08
 */
@Service
public class OpRApplicationTemplateServiceImpl extends ServiceImpl<OpRApplicationTemplateMapper, OpRApplicationTemplateEntity> implements OpRApplicationTemplateService {
}
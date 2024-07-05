package pub.broker.msgbroker.operation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pub.broker.msgbroker.operation.api.entity.OpApplicationEntity;
import pub.broker.msgbroker.operation.mapper.OpApplicationMapper;
import pub.broker.msgbroker.operation.service.OpApplicationService;
import org.springframework.stereotype.Service;
/**
 * 应用管理
 *
 * @author msgbroker
 * @date 2024-06-19 14:51:20
 */
@Service
public class OpApplicationServiceImpl extends ServiceImpl<OpApplicationMapper, OpApplicationEntity> implements OpApplicationService {
}
package pub.broker.msgbroker.operation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pub.broker.msgbroker.operation.api.entity.OpChannelOptionEntity;
import pub.broker.msgbroker.operation.mapper.OpChannelOptionMapper;
import pub.broker.msgbroker.operation.service.OpChannelOptionService;
import org.springframework.stereotype.Service;
/**
 * 渠道的配置项
 *
 * @author msgbroker
 * @date 2024-06-19 14:51:05
 */
@Service
public class OpChannelOptionServiceImpl extends ServiceImpl<OpChannelOptionMapper, OpChannelOptionEntity> implements OpChannelOptionService {
}
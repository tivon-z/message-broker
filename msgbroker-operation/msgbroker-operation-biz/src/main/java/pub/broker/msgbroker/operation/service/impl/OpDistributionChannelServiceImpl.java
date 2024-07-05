package pub.broker.msgbroker.operation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pub.broker.msgbroker.operation.api.entity.OpDistributionChannelEntity;
import pub.broker.msgbroker.operation.mapper.OpDistributionChannelMapper;
import pub.broker.msgbroker.operation.service.OpDistributionChannelService;
import org.springframework.stereotype.Service;
/**
 * 消息下发渠道
 *
 * @author msgbroker
 * @date 2024-06-19 14:49:38
 */
@Service
public class OpDistributionChannelServiceImpl extends ServiceImpl<OpDistributionChannelMapper, OpDistributionChannelEntity> implements OpDistributionChannelService {
}
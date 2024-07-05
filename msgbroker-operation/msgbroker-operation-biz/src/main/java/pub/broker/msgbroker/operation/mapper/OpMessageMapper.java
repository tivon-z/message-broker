package pub.broker.msgbroker.operation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pub.broker.msgbroker.operation.api.entity.OpMessageEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OpMessageMapper extends BaseMapper<OpMessageEntity> {


}
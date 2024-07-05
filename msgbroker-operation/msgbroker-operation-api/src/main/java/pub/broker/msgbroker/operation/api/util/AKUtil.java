package pub.broker.msgbroker.operation.api.util;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import lombok.experimental.UtilityClass;
import org.springframework.data.redis.core.RedisTemplate;
import pub.broker.msgbroker.common.core.util.SpringContextHolder;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@UtilityClass
public class AKUtil {

	private static RedisTemplate<String, String> redisTemplate = null;

	private RedisTemplate<String, String> getRedisTemplate(){
		if (redisTemplate == null){
			redisTemplate= SpringContextHolder.getBean(RedisTemplate.class);
		}
		return redisTemplate;
	}

	public boolean isAKValid(String ak){
		String key = akValidKey(ak);
		return getRedisTemplate().hasKey(key);
	}

	public void setAKValid(String ak, long expireSeconds) {
		String key = akValidKey(ak);
		if (expireSeconds == 0){
			getRedisTemplate().opsForValue().set(key, ak);
		}else{
			getRedisTemplate().opsForValue().set(key, ak, expireSeconds, TimeUnit.SECONDS);
		}
	}



	public int getAKDayLimit(String ak) {
		String key = akDayLimitKey(ak, null);
		String value = getRedisTemplate().opsForValue().get(key);
		if (StrUtil.isBlank(value)) {
			return -99;
		}else{
			return Integer.valueOf(value);
		}
	}

	public void setAKDayLimit(String ak, int dayLimit) {
		String key = akDayLimitKey(ak, null);
		getRedisTemplate().opsForValue().set(key, String.valueOf(dayLimit), 1, TimeUnit.DAYS);
	}

	public Long decrAKDayLimit(String ak) {
		String key = akDayLimitKey(ak, null);
		return getRedisTemplate().opsForValue().decrement(key);
	}

	private String akValidKey(String ak) {
		return "broker:valid:ak:" + ak;
	}

	private String akDayLimitKey(String ak, String yyyyMMdd) {
		if (StrUtil.isBlank(yyyyMMdd)) {
			yyyyMMdd = DateUtil.format(LocalDateTime.now(), DatePattern.PURE_DATE_PATTERN);
		}
		return "broker:limit:"+yyyyMMdd+":ak:" + ak;
	}
}

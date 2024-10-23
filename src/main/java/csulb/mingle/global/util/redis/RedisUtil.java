package csulb.mingle.global.util.redis;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class RedisUtil {

    @Resource(name = "redisTemplate")
    private ValueOperations<String, String> valueOperations;

    public String getData(String key) {
        return valueOperations.get(key);
    }

    public void setData(String key, String value) {
        valueOperations.set(key, value);
    }

    public void setDataExpire(String key, String value, long duration) {
        Duration expireDuration = Duration.ofMinutes(duration);
        valueOperations.set(key, value, expireDuration);
    }

     public void deleteData(String key){
        valueOperations.getOperations().delete(key);
    }
}

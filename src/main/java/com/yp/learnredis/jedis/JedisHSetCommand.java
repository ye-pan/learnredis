package com.yp.learnredis.jedis;

import com.yp.learnredis.HSetCommand;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class JedisHSetCommand extends JedisCommand implements HSetCommand {

    public JedisHSetCommand(String host, int port) {
        super(host, port);
    }

    @Override
    public boolean hset(String cacheKey, String key, String value) {
        return execute(jedis -> jedis.hset(cacheKey, key, value) > 0);
    }

    @Override
    public boolean hmset(String key, Map<String, String> map) {
        return execute(jedis -> {
            String replyStatusCode = jedis.hmset(key, map);
            return STATUS_CODE_OK.equalsIgnoreCase(replyStatusCode);
        });
    }

    @Override
    public Map<String, String> hgetall(String key) {
        return execute(jedis -> jedis.hgetAll(key));
    }

    @Override
    public long hdel(String cacheKey, String... keys) {
        return execute(jedis -> jedis.hdel(cacheKey, keys));
    }

    @Override
    public String hget(String cacheKey, String key) {
        return execute(jedis -> jedis.hget(cacheKey, key));
    }

    @Override
    public long hincrby(String cacheKey, String key, long val) {
        return execute(jedis -> jedis.hincrBy(cacheKey, key, val));
    }

    @Override
    public List<String> hmget(String cacheKey, String... keys) {
        return execute(jedis -> jedis.hmget(cacheKey, keys));
    }

    @Override
    public long hlen(String cacheKey) {
        return execute(jedis -> jedis.hlen(cacheKey));
    }

    @Override
    public Set<String> hkeys(String cacheKey) {
        return execute(jedis -> jedis.hkeys(cacheKey));
    }

    @Override
    public boolean hexists(String cacheKey, String key) {
        return execute(jedis -> jedis.hexists(cacheKey, key));
    }
}

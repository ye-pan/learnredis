package com.yp.learnredis.jedis;

import com.yp.learnredis.HSetCommand;

import java.util.Map;

public class JedisHSetCommand extends JedisCommand implements HSetCommand {

    public JedisHSetCommand(String host, int port) {
        super(host, port);
    }

    @Override
    public boolean hset(String cacheKey, String key, String value) {
        return execute(jedis -> jedis.hset(cacheKey, key, value) > 0);
    }

    @Override
    public void hmset(String key, Map<String, String> map) {
        execute(jedis -> jedis.hmset(key, map));
    }

    @Override
    public Map<String, String> hgetall(String key) {
        return execute(jedis -> jedis.hgetAll(key));
    }

    @Override
    public boolean hdel(String cacheKey, String key) {
        return execute(jedis -> jedis.hdel(cacheKey, key) > 0);
    }

    @Override
    public String hget(String cacheKey, String key) {
        return execute(jedis -> jedis.hget(cacheKey, key));
    }

    @Override
    public void hincrby(String cacheKey, String key, long val) {
        execute(jedis -> jedis.hincrBy(cacheKey, key, val));
    }
}

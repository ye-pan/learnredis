package com.yp.learnredis.jedis;

import com.yp.redis.command.HSetCommand;

import java.util.Map;

public class JedisHSetCommand extends JedisCommand implements HSetCommand {

    public JedisHSetCommand(String host, int port) {
        super(host, port);
    }

    @Override
    public boolean hset(String cacheKey, String key, String value) {
        return execute(jedis->jedis.hset(cacheKey, key, value) > 0);
    }

    @Override
    public boolean hset(String key, Map<String, String> map) {
        return execute(jedis -> jedis.hset(key, map) > 0);
    }

    @Override
    public Map<String, String> hgetall(String key) {
        return execute(jedis->jedis.hgetAll(key));
    }

    @Override
    public boolean hdel(String cacheKey, String key) {
        return execute(jedis->jedis.hdel(cacheKey, key) > 0);
    }
}

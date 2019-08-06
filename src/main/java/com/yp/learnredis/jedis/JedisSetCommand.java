package com.yp.learnredis.jedis;

import com.yp.learnredis.SetCommand;

import java.util.Set;

public class JedisSetCommand extends JedisCommand implements SetCommand {

    public JedisSetCommand(String host, int port) {
        super(host, port);
    }

    @Override
    public long sadd(String key, String... values) {
        return execute(jedis -> jedis.sadd(key, values));
    }

    @Override
    public boolean sismember(String key, String value) {
        return execute(jedis -> jedis.sismember(key, value));
    }

    @Override
    public long srem(String key, String... value) {
        return execute(jedis -> jedis.srem(key, value));
    }

    @Override
    public Set<String> smembers(String key) {
        return execute(jedis -> jedis.smembers(key));
    }

    @Override
    public long scard(String key) {
        return execute(jedis -> jedis.scard(key));
    }

    @Override
    public long smove(String srcKey, String destKey, String val) {
        return execute(jedis -> jedis.smove(srcKey, destKey, val));
    }

    @Override
    public Set<String> sdiff(String... keys) {
        return execute(jedis -> jedis.sdiff(keys));
    }

    @Override
    public Set<String> sinter(String... keys) {
        return execute(jedis -> jedis.sinter(keys));
    }

    @Override
    public Set<String> sunion(String... keys) {
        return execute(jedis -> jedis.sunion(keys));
    }
}

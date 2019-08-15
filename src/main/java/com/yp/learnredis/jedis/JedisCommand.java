package com.yp.learnredis.jedis;

import com.yp.learnredis.Command;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisCommand implements Command {

    private final JedisPool pool;

    public JedisCommand(String host, int port) {
        pool = new JedisPool(host, port);
    }

    public JedisCommand(JedisPool pool) {
        this.pool = pool;
    }

    @Override
    public boolean del(String... keys) {
        return execute(jedis -> jedis.del(keys) > 0);
    }

    @Override
    public boolean expire(String key, int seconds) {
        return execute(jedis -> jedis.expire(key, seconds) > 0);
    }

    @Override
    public long ttl(String key) {
        return execute(jedis -> jedis.ttl(key));
    }

    @Override
    public boolean expireat(String key, long timestamp) {
        return execute(jedis -> jedis.expireAt(key, timestamp) > 0);
    }

    @Override
    public long pttl(String key) {
        return execute(jedis -> jedis.pttl(key));
    }

    @Override
    public boolean pexpire(String key, long milli) {
        return execute(jedis -> jedis.pexpire(key, milli) > 0);
    }

    @Override
    public boolean pexpireat(String key, long timestamp) {
        return execute(jedis -> jedis.pexpireAt(key, timestamp) > 0);
    }

    @Override
    public boolean exists(String key) {
        return execute(jedis -> jedis.exists(key));
    }

    @Override
    public void persist(String key) {
        execute(jedis -> jedis.persist(key));
    }

    protected <T> T execute(Callback<T> callback) {
        Jedis jedis = pool.getResource();
        try {
            return callback.doInJedis(jedis);
        } finally {
            jedis.close();
        }
    }
}

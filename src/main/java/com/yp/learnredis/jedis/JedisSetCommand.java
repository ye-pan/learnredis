package com.yp.learnredis.jedis;

import com.yp.learnredis.SetCommand;

public class JedisSetCommand extends JedisCommand implements SetCommand {

    public JedisSetCommand(String host, int port) {
        super(host, port);
    }

    @Override
    public boolean  sadd(String key, String... values) {
        return execute(jedis->jedis.sadd(key, values) > 0);
    }

    @Override
    public boolean sismember(String key, String value) {
        return execute(jedis->jedis.sismember(key, value));
    }

    @Override
    public boolean srem(String key, String value) {
        return execute(jedis->jedis.srem(key, value) > 0);
    }
}

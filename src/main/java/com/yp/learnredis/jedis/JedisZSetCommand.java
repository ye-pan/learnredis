package com.yp.learnredis.jedis;

import com.yp.redis.command.ZSetCommand;

import java.util.Set;

public class JedisZSetCommand extends JedisCommand implements ZSetCommand {

    public JedisZSetCommand(String host, int port) {
        super(host, port);
    }

    @Override
    public boolean zadd(String key, int weight, String value) {
        return execute(jedis->jedis.zadd(key, weight, value) > 0);
    }

    @Override
    public Set<String> zrange(String key, int start, int end) {
        return execute(jedis->jedis.zrange(key, start, end));
    }
}

package com.yp.learnredis.jedis;

import com.yp.learnredis.ListCommand;

import java.util.List;

public class JedisListCommand extends JedisCommand implements ListCommand {

    public JedisListCommand(String host, int port) {
        super(host, port);
    }

    @Override
    public boolean rpush(String key, String... values) {
        return execute(jedis->jedis.rpush(key, values) > 0);
    }

    @Override
    public List<String> lrange(String key, int start, int end) {
        return execute(jedis->jedis.lrange(key, start, end));
    }

    @Override
    public String lindex(String key, int index) {
        return execute(jedis->jedis.lindex(key, index));
    }

    @Override
    public String lpop(String key) {
        return execute(jedis->jedis.lpop(key));
    }
}

package com.yp.learnredis.jedis;

import com.yp.learnredis.ListCommand;

import java.util.List;

public class JedisListCommand extends JedisCommand implements ListCommand {

    public JedisListCommand(String host, int port) {
        super(host, port);
    }

    @Override
    public long rpush(String key, String... values) {
        return execute(jedis -> jedis.rpush(key, values));
    }

    @Override
    public List<String> lrange(String key, int start, int end) {
        return execute(jedis -> jedis.lrange(key, start, end));
    }

    @Override
    public String lindex(String key, int index) {
        return execute(jedis -> jedis.lindex(key, index));
    }

    @Override
    public String lpop(String key) {
        return execute(jedis -> jedis.lpop(key));
    }

    @Override
    public long lpush(String key, String... values) {
        return execute(jedis -> jedis.lpush(key, values));
    }

    @Override
    public boolean ltrim(String key, int start, int end) {
        return execute(jedis -> {
            String replyStatusCode = jedis.ltrim(key, start, end);
            return STATUS_CODE_OK.equals(replyStatusCode);
        });
    }

    @Override
    public String brpoplpush(String srcKey, String destKey, int timeout) {
        return execute(jedis -> jedis.brpoplpush(srcKey, destKey, timeout));
    }

    @Override
    public List<String> blpop(int timeout, String... keys) {
        return execute(jedis -> jedis.blpop(timeout, keys));
    }
}

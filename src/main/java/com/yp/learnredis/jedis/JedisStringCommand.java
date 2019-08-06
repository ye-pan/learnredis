package com.yp.learnredis.jedis;

import com.yp.learnredis.StringCommand;

public class JedisStringCommand extends JedisCommand implements StringCommand {

    public JedisStringCommand(String host, int port) {
        super(host, port);
    }

    @Override
    public boolean set(String key, String value) {
        return execute(jedis -> {
            String replyStatus = jedis.set(key, value);
            return REPLY_STATUS_OK.equalsIgnoreCase(replyStatus);
        });
    }

    @Override
    public boolean set(String key, long value) {
        String strValue = String.valueOf(value);
        return set(key, strValue);
    }

    @Override
    public boolean set(String key, double value) {
        String strValue = String.valueOf(value);
        return set(key, strValue);
    }

    @Override
    public String get(String key) {
        return execute(jedis -> jedis.get(key));
    }

    @Override
    public long getLong(String key) {
        String value = get(key);
        return Long.parseLong(value);
    }

    @Override
    public double getDouble(String key) {
        String value = get(key);
        return Double.parseDouble(value);
    }

    @Override
    public long incr(String key) {
        return execute(jedis -> jedis.incr(key));
    }

    @Override
    public long incrBy(String key, int val) {
        return execute(jedis -> jedis.incrBy(key, val));
    }

    @Override
    public long decr(String key) {
        return execute(jedis -> jedis.decr(key));
    }

    @Override
    public long decrBy(String key, int val) {
        return execute(jedis -> jedis.decrBy(key, val));
    }

    @Override
    public long append(String key, String value) {
        return execute(jedis -> jedis.append(key, value));
    }

    @Override
    public String getRange(String key, int start, int end) {
        return execute(jedis -> jedis.getrange(key, start, end));
    }

    @Override
    public double incrByFloat(String key, double val) {
        return execute(jedis -> jedis.incrByFloat(key, val));
    }

    @Override
    public long setRange(String key, int index, String str) {
        return execute(jedis -> jedis.setrange(key, index, str));
    }

    @Override
    public boolean setbit(String key, int offset, boolean val) {
        return execute(jedis -> jedis.setbit(key, offset, val));
    }
}

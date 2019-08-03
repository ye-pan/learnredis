package com.yp.learnredis;

public interface SetCommand extends Command {
    boolean  sadd(String key, String... values);

    boolean sismember(String key, String value);

    boolean srem(String key, String value);
}

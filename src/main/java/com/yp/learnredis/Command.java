package com.yp.learnredis;

public interface Command {
    boolean del(String... key);

    boolean expire(String key, int seconds);

    long ttl(String key);

    boolean expireat(String key, long timestamp);

    long pttl(String key);

    boolean pexpire(String key, long milli);

    boolean pexpireat(String key, long timestamp);

    boolean exists(String key);
}

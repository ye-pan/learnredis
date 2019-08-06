package com.yp.learnredis;

import java.util.Set;

public interface SetCommand extends Command {

    long sadd(String key, String... values);

    boolean sismember(String key, String value);

    long srem(String key, String... value);

    Set<String> smembers(String key);

    long scard(String key);

    long smove(String srcKey, String destKey, String val);

    Set<String> sdiff(String... keys);

    Set<String> sinter(String... keys);

    Set<String> sunion(String... keys);
}

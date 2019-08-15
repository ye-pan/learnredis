package com.yp.learnredis;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface HSetCommand extends Command {

    boolean hset(String cacheKey, String key, String value);

    boolean hmset(String key, Map<String, String> map);

    Map<String, String> hgetall(String key);

    long hdel(String cacheKey, String... key);

    String hget(String cacheKey, String key);

    long hincrby(String cacheKey, String key, long val);

    List<String> hmget(String cacheKey, String... keys);

    long hlen(String cacheKey);

    Set<String> hkeys(String cacheKey);

    boolean hexists(String cacheKey, String key);

    double hincrByFloat(String cacheKey, String key, double val);
}

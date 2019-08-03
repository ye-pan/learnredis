package com.yp.learnredis;

import java.util.Map;

public interface HSetCommand extends Command {

    boolean hset(String cacheKey, String key, String value);

    boolean hset(String key, Map<String, String> map);

    Map<String, String> hgetall(String key);

    boolean hdel(String cacheKey, String key);
}

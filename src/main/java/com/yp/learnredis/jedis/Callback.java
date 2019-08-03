package com.yp.learnredis.jedis;

import redis.clients.jedis.Jedis;

public interface Callback<T> {
    T doInJedis(Jedis jedis);
}

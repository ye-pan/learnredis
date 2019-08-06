package com.yp.learnredis;

import java.util.List;

public interface ListCommand extends Command {
    long rpush(String key, String... values);

    List<String> lrange(String key, int start, int end);

    String lindex(String key, int index);

    String lpop(String key);

    long lpush(String key, String... values);

    boolean ltrim(String key, int start, int end);

    String brpoplpush(String srcKey, String destKey, int timeout);

    List<String> blpop(int timeout, String... keys);
}

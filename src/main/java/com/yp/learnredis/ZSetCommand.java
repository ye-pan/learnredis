package com.yp.learnredis;

import java.util.Set;

public interface ZSetCommand extends Command {
    boolean zadd(String key, double score, String value);

    Set<String> zrange(String key, int start, int end);

    Set<Member> zrangeWithscores(String key, int start, int end);

    Set<String> zrangeByScore(String key, double min, double max);

    boolean zrem(String key, String value);
}

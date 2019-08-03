package com.yp.learnredis;

import java.util.Set;

public interface ZSetCommand extends Command {
    boolean zadd(String key, int weight, String value);

    Set<String> zrange(String key, int start, int end);
}

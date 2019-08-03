package com.yp.learnredis;

import java.util.List;

public interface ListCommand extends Command {
    boolean rpush(String key, String... values);

    List<String> lrange(String key, int start, int end);

    String lindex(String key, int index);

    String lpop(String key);
}

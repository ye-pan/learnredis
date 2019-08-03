package com.yp.learnredis;

public interface StringCommand extends Command {
    String REPLY_STATUS_OK = "OK";

    boolean set(String key, String value);

    boolean set(String key, long value);

    boolean set(String key, double value);

    String get(String key);

    long getLong(String key);

    double getDouble(String key);

    long incr(String key);

    long incrBy(String key, int val);

    long decr(String key);

    long decrBy(String longValKey, int val);

    boolean append(String key, String value);

    String getRange(String key, int start, int end);
}

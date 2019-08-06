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

    long decrBy(String key, int val);

    long append(String key, String value);

    String getRange(String key, int start, int end);

    double incrByFloat(String key, double val);

    long setRange(String key, int index, String str);

    boolean setbit(String key, int offset, boolean val);
}

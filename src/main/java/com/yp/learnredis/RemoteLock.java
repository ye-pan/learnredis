package com.yp.learnredis;

public interface RemoteLock {
    String lock(String key, long wait);

    void release(String key, String identifier);

    String lock(String key, long wait, long timeout);
}

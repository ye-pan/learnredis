package com.yp.learnredis;

public interface Command {
    String STATUS_CODE_OK = "OK";

    boolean del(String... key);

    /**
     * 让给定键指定秒数后过期
     * @param key
     * @param seconds
     * @return
     */
    boolean expire(String key, int seconds);

    /**
     * 查看给定键距离过期还有多少秒
     * @param key
     * @return
     */
    long ttl(String key);

    /**
     * 将给定键的过期时间设置为给定的UNIX时间戳
     * @param key
     * @param timestamp
     * @return
     */
    boolean expireat(String key, long timestamp);

    /**
     * 查看给定键距离过期时间还有多少毫秒
     * @param key
     * @return
     */
    long pttl(String key);

    boolean pexpire(String key, long milli);

    boolean pexpireat(String key, long timestamp);

    boolean exists(String key);

    /**
     * 移除键的过期时间
     * @param key
     */
    void persist(String key);
}

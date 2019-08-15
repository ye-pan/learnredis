package com.yp.learnredis;

import redis.clients.jedis.ZParams;

import java.util.Map;
import java.util.Set;

public interface ZSetCommand extends Command {
    boolean zadd(String key, double score, String value);

    Set<String> zrange(String key, int start, int end);

    Set<Member> zrangeWithscores(String key, int start, int end);

    Set<String> zrangeByScore(String key, double min, double max);

    boolean zrem(String key, String... value);

    double zscore(String key, String value);

    double zincry(String key, String value, double score);

    Set<String> zrevrange(String key, int start, int end);

    /**
     * 求两个集合的交集，并将结果存在destKey，
     * @param destKey 结果存放，是一个set结构
     * @param aggregateParam 使用AGGREGATE选项，你可以指定并集的结果集的聚合方式。
     *                       默认使用的参数SUM，可以将所有集合中某个成员的score值之和作为结果集中该成员的score值。
     *                       如果使用参数MIN或者MAX，结果集就是所有集合中元素最小或最大的元素。
     * @param values 求交集的集合
     */
    long zinterstore(String destKey, ZParams.Aggregate aggregateParam, String... values);

    void zremrangeByRank(String key, int start, int end);

    long zcard(String key);

    long zadd(String key, Map<String, Double> map);

    long zcount(String key, double min, double max);

    long zrank(String key, String member);

    long zunionstore(String destKey, ZParams.Aggregate aggregateParam, String... sourceKeys);
}

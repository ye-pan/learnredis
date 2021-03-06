package com.yp.learnredis.jedis;

import com.yp.learnredis.Member;
import com.yp.learnredis.ZSetCommand;
import redis.clients.jedis.ZParams;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class JedisZSetCommand extends JedisCommand implements ZSetCommand {

    public JedisZSetCommand(String host, int port) {
        super(host, port);
    }

    @Override
    public boolean zadd(String key, double score, String value) {
        return execute(jedis -> jedis.zadd(key, score, value) > 0);
    }

    @Override
    public Set<String> zrange(String key, int start, int end) {
        return execute(jedis -> jedis.zrange(key, start, end));
    }

    @Override
    public Set<Member> zrangeWithscores(String key, int start, int end) {
        return execute(jedis ->
                jedis.zrangeWithScores(key, start, end)
                        .stream()
                        .map(tuple -> new Member(tuple.getScore(), tuple.getElement()))
                        .collect(Collectors.toSet()));
    }

    @Override
    public Set<String> zrangeByScore(String key, double min, double max) {
        return execute(jedis -> jedis.zrangeByScore(key, min, max));
    }

    @Override
    public boolean zrem(String key, String... values) {
        return execute(jedis -> jedis.zrem(key, values) > 0);
    }

    @Override
    public double zscore(String key, String value) {
        return execute(jedis -> jedis.zscore(key, value));
    }

    @Override
    public double zincry(String key, String value, double score) {
        return execute(jedis -> jedis.zincrby(key, score, value));
    }

    @Override
    public Set<String> zrevrange(String key, int start, int end) {
        return execute(jedis -> jedis.zrevrange(key, start, end));
    }

    @Override
    public long zinterstore(String destKey, ZParams.Aggregate aggregateParam, String... values) {
        ZParams params = new ZParams().aggregate(aggregateParam);
        return execute(jedis -> jedis.zinterstore(destKey, params, values));
    }

    @Override
    public void zremrangeByRank(String key, int start, int end) {
        execute(jedis -> jedis.zremrangeByRank(key, start, end));
    }

    @Override
    public long zcard(String key) {
        return execute(jedis -> jedis.zcard(key));
    }

    @Override
    public long zadd(String key, Map<String, Double> map) {
        return execute(jedis -> jedis.zadd(key, map));
    }

    @Override
    public long zcount(String key, double min, double max) {
        return execute(jedis -> jedis.zcount(key, min, max));
    }

    @Override
    public long zrank(String key, String member) {
        return execute(jedis -> jedis.zrank(key, member));
    }

    @Override
    public long zunionstore(String destKey, ZParams.Aggregate aggregateParam, String... sourceKeys) {
        ZParams params = new ZParams().aggregate(aggregateParam);
        return execute(jedis -> jedis.zunionstore(destKey, params, sourceKeys));
    }
}

package com.yp.learnredis.jedis;

import com.yp.learnredis.Member;
import com.yp.learnredis.ZSetCommand;

import java.util.Set;
import java.util.stream.Collectors;

public class JedisZSetCommand extends JedisCommand implements ZSetCommand {

    public JedisZSetCommand(String host, int port) {
        super(host, port);
    }

    @Override
    public boolean zadd(String key, double score, String value) {
        return execute(jedis->jedis.zadd(key, score, value) > 0);
    }

    @Override
    public Set<String> zrange(String key, int start, int end) {
        return execute(jedis->jedis.zrange(key, start, end));
    }

    @Override
    public Set<Member> zrangeWithscores(String key, int start, int end) {
        return execute(jedis ->
                jedis.zrangeWithScores(key, start, end)
                        .stream()
                        .map(tuple->new Member(tuple.getScore(), tuple.getElement()))
                        .collect(Collectors.toSet()));
    }

    @Override
    public Set<String> zrangeByScore(String key, double min, double max) {
        return execute(jedis -> jedis.zrangeByScore(key, min, max));
    }

    @Override
    public boolean zrem(String key, String value) {
        return execute(jedis -> jedis.zrem(key, value) > 0);
    }
}

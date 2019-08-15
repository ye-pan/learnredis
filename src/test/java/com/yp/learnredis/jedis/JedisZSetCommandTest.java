package com.yp.learnredis.jedis;

import com.yp.learnredis.Member;
import com.yp.learnredis.ZSetCommand;
import org.junit.Test;
import redis.clients.jedis.ZParams;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

import static org.junit.Assert.*;

public class JedisZSetCommandTest {
    private ZSetCommand command = new JedisZSetCommand(Remotes.HOST, Remotes.PORT);

    @Test
    public void testOp() {
        String key = "zset-key";
        command.del(key);
        command.zadd(key, 728, "member1");
        boolean r = command.zadd(key, 982, "member0");
        assertTrue(r);

        r = command.zadd(key, 982, "member0");
        assertFalse(r);

        Set<String> set = command.zrange(key, 0, -1);
        assertEquals(2, set.size());
        set.forEach(System.out::println);

        Set<Member> memberSet = command.zrangeWithscores(key, 0, -1);
        assertEquals(2, memberSet.size());
        memberSet.forEach(System.out::println);

        set = command.zrangeByScore(key, 0, 800);
        assertEquals(1, set.size());
        set.forEach(System.out::println);

        r = command.zrem(key, "member1");
        assertTrue(r);
        r = command.zrem(key, "member1");
        assertFalse(r);
    }

    @Test
    public void testZSetOp() {
        String key = "zset-key";
        command.del(key);

        Map<String, Double> map = Map.of("a", 3D, "b", 2D, "c", 1D);
        long r = command.zadd(key, map);
        assertEquals(map.size(), r);

        double v = command.zincry(key, "c", 3);
        assertEquals(4, v, 0);

        r = command.zcount(key, 0D, 3D);
        assertEquals(2, r);

        r = command.zrank(key, "b");
        assertEquals(0, r);

        Set<String> set = command.zrange(key, 0, -1);
        assertEquals(set, map.keySet());
    }

    @Test
    public void testZSetOp2() {
        String key1 = "zset-1";
        String key2 = "zset-2";
        command.del(key1, key2);
        Map<String, Double> map1 = Map.of("a", 1D, "b", 2D, "c", 3D);
        command.zadd(key1, map1);
        Map<String, Double> map2 = Map.of("b", 4D, "c", 1D, "d", 0D);
        command.zadd(key2, map2);


        String zinterKey = "zset-i";
        command.del(zinterKey);
        long r = command.zinterstore(zinterKey, ZParams.Aggregate.SUM, key1, key2);
        assertEquals(2, r);
        Set<String> set = command.zrange(zinterKey, 0, -1);
        assertEquals(Set.of("b", "c"), set);


        String zunionKey = "zset-u";
        command.del(zunionKey);
        r = command.zunionstore(zunionKey, ZParams.Aggregate.MIN, key1, key2);
        assertEquals(4, r);
        set = command.zrange(zunionKey, 0, -1);
        assertEquals(Set.of("a", "b", "c", "d"), set);
    }
}
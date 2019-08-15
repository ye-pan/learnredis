package com.yp.learnredis.jedis;

import com.yp.learnredis.HSetCommand;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class JedisHSetCommandTest {
    private HSetCommand command = new JedisHSetCommand(Remotes.HOST, Remotes.PORT);

    @Test
    public void testOp() {
        String cacheKey = "hash-key";
        command.del(cacheKey);

        Map<String, String> map = new HashMap<>();
        map.put("sub-key1", "value1");
        map.put("sub-key2", "value2");
        command.hmset(cacheKey, map);
        command.hset(cacheKey, "sub-key3", "value3");

        map = command.hgetall(cacheKey);
        assertEquals(3, map.size());

        long r = command.hdel(cacheKey, "sub-key2");
        assertEquals(1, r);
        r = command.hdel(cacheKey, "sub-key2");
        assertEquals(0, r);

        String val = command.hget(cacheKey, "sub-key1");
        assertEquals("value1", val);
    }

    @Test
    public void testHSetOp() {
        String cacheKey = "hash-key";
        command.del(cacheKey);
        Map<String, String> map = new HashMap<>();
        map.put("k1", "v1");
        map.put("k2", "v2");
        map.put("k3", "v3");
        boolean r = command.hmset(cacheKey, map);
        assertTrue(r);

        List<String> list = command.hmget(cacheKey, "k2", "k3");
        assertEquals(List.of("v2", "v3"), list);
        long v = command.hlen(cacheKey);
        assertEquals(3, v);
        command.hdel(cacheKey, "k1", "k3");


    }

    @Test
    public void testHSetSOp() {
        String cacheKey = "hash-key";
        command.del(cacheKey);
        Map<String, String> map = new HashMap<>();
        map.put("short", "hello");
        map.put("long", "1000");
        command.hmset(cacheKey, map);
        Set<String> set = command.hkeys(cacheKey);
        assertEquals(Set.of("long", "short"), set);
        boolean r = command.hexists(cacheKey, "num");
        assertFalse(r);
        long v = command.hincrby(cacheKey, "num", 1);
        assertEquals(1, v);
        double floatVal = command.hincrByFloat(cacheKey, "floatNum", 1.1D);
        assertEquals(1.1, floatVal, 0);
    }
}
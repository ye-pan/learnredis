package com.yp.learnredis.jedis;

import com.yp.learnredis.SetCommand;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class JedisSetCommandTest {
    private SetCommand command = new JedisSetCommand(Remotes.HOST, Remotes.PORT);

    @Test
    public void testOp() {
        String key = "set-key";
        command.del(key);

        command.sadd(key, "item", "item2", "item3");

        Set<String> set = command.smembers(key);
        assertEquals(3, set.size());

        boolean r = command.sismember(key, "item4");
        assertFalse(r);

        r = command.sismember(key, "item2");
        assertTrue(r);

        long v = command.srem(key, "item2");
        assertEquals(1, v);

        v = command.srem(key, "item2");
        assertEquals(0, v);
    }

    @Test
    public void testSetOp() {
        String key = "set-key";
        String key2 = "set-key2";
        command.del(key, key2);

        long r = command.sadd(key, "a", "b", "c");
        assertEquals(3, r);
        r = command.srem(key, "c", "d");
        assertEquals(1, r);
        r = command.srem(key, "c", "d");
        assertEquals(0, r);
        r = command.scard(key);
        assertEquals(2, r);

        r = command.smove(key, key2, "a");
        assertEquals(1, r);
        r = command.smove(key, key2, "c");
        assertEquals(0, r);
        Set<String> set = command.smembers(key2);
        assertEquals(Set.of("a"), set);
    }

    @Test
    public void testColOp() {
        String key1 = "skey1";
        String key2 = "skey2";
        command.del(key1, key2);

        command.sadd(key1, "a", "b", "c", "d");
        command.sadd(key2, "c", "d", "e", "f");

        Set<String> set = command.sdiff(key1, key2);
        assertEquals(Set.of("a", "b"), set);
        set = command.sinter(key1, key2);
        assertEquals(Set.of("c", "d"), set);
        set = command.sunion(key1, key2);
        assertEquals(Set.of("a", "b", "c", "d", "e", "f"), set);
    }
}
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

        r = command.srem(key, "item2");
        assertTrue(r);

        r = command.srem(key, "item2");
        assertFalse(r);
    }
}
package com.yp.learnredis.jedis;

import com.yp.learnredis.StringCommand;
import org.junit.Test;

import static org.junit.Assert.*;

public class JedisStringCommandTest {
    private StringCommand command = new JedisStringCommand(Remotes.HOST, Remotes.PORT);

    @Test
    public void testGetAndSet() {
        String key = "hello";
        String value = "world";
        command.set(key, value);

        String val = command.get(key);
        assertEquals(value, val);

        command.del(key);
        val = command.get(key);
        assertNull(val);
    }

    @Test
    public void testNumberOp() {
        String key = "key";
        command.del(key);
        long r = command.incr(key);
        assertEquals(1, r);
        r = command.incrBy(key, 15);
        assertEquals(16, r);

        r = command.decr(key);
        assertEquals(15, r);
        r = command.decrBy(key, 5);
        assertEquals(10, r);

        double val = 1.1;
        double v = command.incrByFloat(key, val);
        assertEquals(11.1, v, 0);
    }

    @Test
    public void testStrOp() {
        String key = "new-string-key";
        command.del(key);
        long r = command.append(key, "hello ");
        assertEquals(6, r);
        r = command.append(key, "world!");
        assertEquals(12, r);

        String str = command.getRange(key, 3, 7);
        assertEquals("lo wo", str);
        r = command.setRange(key, 6, "W");
        assertEquals(12, r);
        String val = command.get(key);
        assertEquals("hello World!", val);
        r = command.setRange(key, 11, ", how are you?");
        assertEquals(25, r);
        val = command.get(key);
        assertEquals("hello World, how are you?", val);
    }

    @Test
    public void testBitOp() {
        String key = "another-key";
        command.del(key);
        boolean r = command.setbit(key, 2, true);
        assertFalse(r);
        r = command.setbit(key, 7, true);
        assertFalse(r);
        String val = command.get(key);
        assertEquals("!", val);
    }
}
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
    public void getLong() {
    }

    @Test
    public void getDouble() {
    }

    @Test
    public void incr() {
    }

    @Test
    public void incrBy() {
    }

    @Test
    public void decr() {
    }

    @Test
    public void decrBy() {
    }

    @Test
    public void append() {
    }

    @Test
    public void getRange() {
    }
}
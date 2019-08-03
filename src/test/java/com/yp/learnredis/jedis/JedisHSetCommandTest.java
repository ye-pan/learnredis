package com.yp.learnredis.jedis;

import com.yp.learnredis.HSetCommand;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
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

        boolean r = command.hdel(cacheKey, "sub-key2");
        assertTrue(r);
        r = command.hdel(cacheKey, "sub-key2");
        assertFalse(r);

        String val = command.hget(cacheKey, "sub-key1");
        assertEquals("value1", val);
    }
}
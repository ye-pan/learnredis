package com.yp.learnredis.jedis;

import com.yp.learnredis.ListCommand;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class JedisListCommandTest {

    private ListCommand command = new JedisListCommand(Remotes.HOST, Remotes.PORT);

    @Test
    public void rpush() {
        String key = "list-key";
        command.del(key);
        command.rpush(key, "item", "item2", "item");
        List<String> list = command.lrange(key, 0, -1);
        assertEquals(3, list.size());

        String val = command.lindex(key, 1);
        assertEquals("item2", val);

        command.lpop(key);
        list = command.lrange(key, 0, -1);
        assertEquals(2, list.size());
    }
}
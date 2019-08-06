package com.yp.learnredis.jedis;

import com.yp.learnredis.ListCommand;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class JedisListCommandTest {

    private ListCommand command = new JedisListCommand(Remotes.HOST, Remotes.PORT);

    @Test
    public void testListOp() {
        String key = "list-key";
        command.del(key);

        long r = command.rpush(key, "last");
        assertEquals(1, r);
        r = command.lpush(key, "first");
        assertEquals(2, r);
        r = command.rpush(key, "new last");
        assertEquals(3, r);
        List<String> list = command.lrange(key, 0, -1);
        assertEquals(3, list.size());
        assertEquals(List.of("first", "last", "new last"), list);

        String val = command.lpop(key);
        assertEquals("first", val);
        val = command.lpop(key);
        assertEquals("last", val);
        list = command.lrange(key, 0, -1);
        assertEquals(List.of("new last"), list);

        r = command.rpush(key, "a", "b", "c");
        assertEquals(4, r);

        boolean v = command.ltrim(key, 2, -1);//可以从列表的左端或者右端删除任意数量元素
        assertTrue(v);
        list = command.lrange(key, 0, -1);
        assertEquals(2, list.size());
        assertEquals(List.of("b", "c"), list);
    }

    @Test
    public void testBlockOp() {
        String key1 = "list";
        String key2 = "list2";
        command.del(key1, key2);

        command.rpush(key1, "item1");
        command.rpush(key1, "item2");
        command.rpush(key2, "item3");

        String val = command.brpoplpush(key2, key1, 1);
        assertEquals("item3", val);
        command.brpoplpush(key2, key1, 1);

        List<String> list = command.lrange(key1, 0, -1);
        assertEquals(List.of("item3", "item1", "item2"), list);

        command.brpoplpush(key1, key2, 1);

        list = command.blpop(1, key1, key2);
        System.out.println(list);
        list = command.blpop(1, key1, key2);
        System.out.println(list);
        list = command.blpop(1, key1, key2);
        System.out.println(list);
        command.blpop(1, key1, key2);
    }
}
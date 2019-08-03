package com.yp.learnredis.jedis;

import com.yp.learnredis.Member;
import com.yp.learnredis.ZSetCommand;
import org.junit.Test;

import java.util.Set;

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
}
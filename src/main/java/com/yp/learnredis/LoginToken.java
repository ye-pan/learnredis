package com.yp.learnredis;

import com.yp.learnredis.jedis.JedisHSetCommand;
import com.yp.learnredis.jedis.JedisZSetCommand;
import java.util.Set;

public class LoginToken {

    private static final int LIMIT = 10000000;

    private final HSetCommand hSetCommand;

    private final ZSetCommand zSetCommand;

    public LoginToken(String host, int port) {
        hSetCommand = new JedisHSetCommand(host, port);
        zSetCommand = new JedisZSetCommand(host, port);
    }

    public String checkToken(String token) {
        return hSetCommand.hget("login:", token);
    }

    public void updateToken(String token, String userId, String item) {
        long timestamp = System.currentTimeMillis();
        hSetCommand.hset("login:", token, userId);
        zSetCommand.zadd("recent:", timestamp, token);
        if(item != null && item.length() > 0) {
            zSetCommand.zadd(userViewedKey(token), timestamp, item);
            zSetCommand.zremrangeByRank(userViewedKey(token), 0, -26);
        }
    }

    public void clearSessions() {
        while(true) {
            int size = (int)zSetCommand.zcard("recent:");
            if(size <= LIMIT) {
                //TODO sleep ?
                Thread.yield();
                continue;
            }
            int end = Math.min(size - LIMIT, 100);
            Set<String> tokens = zSetCommand.zrange("recent", 0, end - 1);
            String[] sessionKeys = new String[tokens.size()];
            tokens.toArray(sessionKeys);
            zSetCommand.del(sessionKeys);
            hSetCommand.hdel("login:", sessionKeys);
            zSetCommand.zrem("recent:", sessionKeys);
        }
    }

    private String userViewedKey(String token) {
        return "viewed:" + token;
    }
}

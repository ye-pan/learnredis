package com.yp.learnredis;

import com.yp.learnredis.jedis.JedisHSetCommand;
import com.yp.learnredis.jedis.JedisSetCommand;
import com.yp.learnredis.jedis.JedisStringCommand;
import com.yp.learnredis.jedis.JedisZSetCommand;
import redis.clients.jedis.ZParams;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Vote {
    private static final int ONE_WEEK_IN_SECONDS = 7 * 86400;
    private static final int VOTE_SCORE = 342;
    private static final int ARTICLES_PRE_PAGE = 25;

    private SetCommand setCommand;

    private ZSetCommand zSetCommand;

    private HSetCommand hSetCommand;

    private StringCommand stringCommand;

    public Vote(String host, int port) {
        setCommand = new JedisSetCommand(host, port);
        zSetCommand = new JedisZSetCommand(host, port);
        hSetCommand = new JedisHSetCommand(host, port);
        stringCommand = new JedisStringCommand(host, port);
    }

    public void articleVote(Long userId, Long articleId) {
        long cutoff = (System.currentTimeMillis() / 1000) - ONE_WEEK_IN_SECONDS;
        String articleKey = articleKey(articleId);
        if(zSetCommand.zscore("time:", articleKey) < cutoff) {
            return;
        }
        if(setCommand.sadd(votedUserKey(articleId), userId.toString())) {
            zSetCommand.zincry("score:", articleKey, VOTE_SCORE);
            hSetCommand.hincrby(articleKey, "votes", 1);
        }
    }

    public long postArticle(Long userId, String title, String link) {
        long articleId = stringCommand.incr("article:");
        String voted = votedUserKey(userId);
        setCommand.sadd(voted, userId.toString());
        setCommand.expire(voted, ONE_WEEK_IN_SECONDS);

        long now = System.currentTimeMillis();
        String articleKey = articleKey(articleId);
        Map<String, String> article = new HashMap<>();
        article.put("id", String.valueOf(articleId));
        article.put("title", title);
        article.put("link", link);
        article.put("poster", userId.toString());
        article.put("time", String.valueOf(now));
        article.put("votes", "1");
        hSetCommand.hmset(articleKey, article);
        zSetCommand.zadd("score:", now + VOTE_SCORE, articleKey);
        zSetCommand.zadd("time:", now, articleKey);
        return articleId;
    }

    public List<Map<String, String>> getArticles(String order, int page) {
        int start = (page - 1) * ARTICLES_PRE_PAGE;
        int end = start + ARTICLES_PRE_PAGE - 1;

        Set<String> ids = zSetCommand.zrevrange(order, start, end);
        return ids
                .stream()
                .map(id -> hSetCommand.hgetall(id))
                .collect(Collectors.toList());
    }

    public void addRemoveGroups(Long articleId, List<String> toAdd, List<String> toRemove) {
        String articleKey = articleKey(articleId);
        toAdd.forEach(group -> setCommand.sadd(groupKey(group), articleKey));
        toRemove.forEach(group -> setCommand.srem(groupKey(group), articleKey));
    }

    private String groupKey(String group) {
        return "group:" + group;
    }

    private String votedUserKey(Long articleId) {
        return "voted:" + articleId;
    }

    private String articleKey(Long articleId) {
        return "article:" + articleId;
    }

    public List<Map<String, String>> getGroupArticles(String group, String order, int page) {
        String key = mergeGroupArticlesKeyResult(group, order);
        if(!zSetCommand.exists(key)) {
            zSetCommand.zinterstore(key, ZParams.Aggregate.MAX, groupKey(group), order);
            zSetCommand.expire(key, 60);
        }
        return getArticles(key, page);
    }

    private String mergeGroupArticlesKeyResult(String group, String order) {
        return order + group;
    }
}

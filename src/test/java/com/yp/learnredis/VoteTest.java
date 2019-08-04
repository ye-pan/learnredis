package com.yp.learnredis;

import com.yp.learnredis.jedis.Remotes;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class VoteTest {

    private Vote vote = new Vote(Remotes.HOST, Remotes.PORT);

    @Test
    public void testPost() {
        long articleId = vote.postArticle(0L, "A title", "https://www.baidu.com");
        assertTrue(articleId > 0);
    }

    @Test
    public void testGetArticles() {
        List<Map<String, String>> articles = vote.getArticles("score:", 1);
        assertEquals(2, articles.size());
        articles.forEach(System.out::println);
    }

    @Test
    public void testArticleVote() {
        vote.articleVote(1L, 2L);
    }
}
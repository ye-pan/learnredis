package com.yp.learnredis.vote;

import com.yp.learnredis.jedis.Remotes;
import com.yp.learnredis.vote.Vote;
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

    @Test
    public void testAddRemoveGroup() {
        vote.addRemoveGroups(2L, List.of("sample"), List.of(""));
    }

    @Test
    public void testGetGroupArticles() {
        List<Map<String, String>> theGroupArticles = vote.getGroupArticles("sample", "score:", 1);
        assertEquals(1, theGroupArticles.size());
        theGroupArticles.forEach(System.out::println);
    }
}
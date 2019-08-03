package com.yp.learnredis;

public class Member {
    private final double score;
    private final String value;

    public Member(double score, String value) {
        this.score = score;
        this.value = value;
    }

    public double getScore() {
        return score;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "{score=" + score + ", value=" + value + "}";
    }
}

package com.example.huyva.englishgrammer.objects;

/**
 * Created by huyva on 2/4/2018.
 */

public class ScoreInfo {
    private String previousScoreString;
    private int score;
    private int maxScore;

    public String getPreviousScoreString() {
        return previousScoreString;
    }

    public void setPreviousScoreString(String previousScoreString) {
        this.previousScoreString = previousScoreString;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public ScoreInfo(String previousScoreString, int score, int maxScore) {
        this.previousScoreString = previousScoreString;
        this.score = score;
        this.maxScore = maxScore;
    }
}

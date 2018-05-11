package com.huyha.van.englishgrammer.objects;

import java.io.Serializable;

/**
 * Created by huyva on 1/19/2018.
 */

public class Unit implements Serializable{
    private String nameUnit;
    private String urlGrammer;
    private String urlExercise;
    private boolean isFavorite;
    private int score;
    private int maxScore;

    public Unit(String nameUnit, String urlGrammer, String urlExercise, boolean isFavorite){
        this.nameUnit = nameUnit;
        this.urlGrammer = urlGrammer;
        this.urlExercise = urlExercise;
        this.isFavorite = isFavorite;
        this.score = 0;
        this.maxScore = 0;
    }

    public Unit(String nameUnit, String urlGrammer, String urlExercise, boolean isFavorite, int score, int maxScore) {
        this.nameUnit = nameUnit;
        this.urlGrammer = urlGrammer;
        this.urlExercise = urlExercise;
        this.isFavorite = isFavorite;
        this.score = score;
        this.maxScore = maxScore;
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

    public String getNameUnit() {
        return nameUnit;
    }

    public void setNameUnit(String nameUnit) {
        this.nameUnit = nameUnit;
    }

    public String getUrlGrammer() {
        return urlGrammer;
    }

    public void setUrlGrammer(String urlGrammer) {
        this.urlGrammer = urlGrammer;
    }

    public String getUrlExercise() {
        return urlExercise;
    }

    public void setUrlExercise(String urlExercise) {
        this.urlExercise = urlExercise;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}

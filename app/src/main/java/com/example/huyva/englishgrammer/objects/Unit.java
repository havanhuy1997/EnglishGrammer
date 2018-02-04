package com.example.huyva.englishgrammer.objects;

import java.io.Serializable;

/**
 * Created by huyva on 1/19/2018.
 */

public class Unit implements Serializable{
    private String nameUnit;
    private String urlGrammer;
    private String urlExercise;

    public Unit(String nameUnit, String urlGrammer, String urlExercise){
        this.nameUnit = nameUnit;
        this.urlGrammer = urlGrammer;
        this.urlExercise = urlExercise;
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
}

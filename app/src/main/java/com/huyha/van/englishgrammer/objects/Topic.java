package com.huyha.van.englishgrammer.objects;

import java.io.Serializable;

/**
 * Created by huyva on 1/19/2018.
 */

public class Topic implements Serializable {
    private String nameTopic;
    private boolean isVip;

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }

    public Topic(String nameTopic){
        this.nameTopic = nameTopic;
    }

    public Topic(String nameTopic, boolean isVip){
        this.isVip = isVip;
        this.nameTopic = nameTopic;
    }

    public String getNameTopic() {
        return nameTopic;
    }

    public void setNameTopic(String nameTopic) {
        this.nameTopic = nameTopic;
    }

}

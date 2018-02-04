package com.example.huyva.englishgrammer.objects;

import java.io.Serializable;

/**
 * Created by huyva on 1/19/2018.
 */

public class Topic implements Serializable {
    private String nameTopic;

    public Topic(String nameTopic){
        this.nameTopic = nameTopic;
    }

    public String getNameTopic() {
        return nameTopic;
    }

    public void setNameTopic(String nameTopic) {
        this.nameTopic = nameTopic;
    }
}

package com.example.huyva.englishgrammer.utils;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

/**
 * Created by huyva on 1/19/2018.
 */

public class TxtToSpeech {
    static private String TAG = "TextToSpeech";
    private Context context;
    private TextToSpeech textToSpeech;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public TextToSpeech getTextToSpeech() {
        return textToSpeech;
    }

    public void setTextToSpeech(TextToSpeech textToSpeech) {
        this.textToSpeech = textToSpeech;
    }

    public TxtToSpeech(Context context){
        this.context = context;
        textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i != TextToSpeech.ERROR){
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });
    }

    public void pauseSpeech(){
        if (textToSpeech != null){
            textToSpeech.stop();
        }
    }

    public void destroySpeech(){
        if (textToSpeech != null){
            textToSpeech.shutdown();
        }
    }

    public void changeSpeech(float rate){
        if (textToSpeech != null){
            textToSpeech.setSpeechRate(rate);
        }
    }

    public void speak(String text){
        if (textToSpeech != null){
            textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH, null);
        }
    }
}

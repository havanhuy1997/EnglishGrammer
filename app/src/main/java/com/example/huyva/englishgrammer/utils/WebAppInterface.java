package com.example.huyva.englishgrammer.utils;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.example.huyva.englishgrammer.activities.learningActivity.LearningPresenter;
import com.example.huyva.englishgrammer.objects.ScoreInfo;

/**
 * Created by huyva on 1/19/2018.
 */

public class WebAppInterface {
    static final String TAG = "WebApiInterface";
    static private Context mContext;
    private TxtToSpeech txtToSpeech;
    private LearningPresenter learningPresenter;
    private static WebAppInterface instance;
    private String unitName;

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public static WebAppInterface getInstance(){
        if (instance == null){
            instance = new WebAppInterface(mContext);
            return instance;
        }
        return instance;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public TxtToSpeech getTxtToSpeech() {
        return txtToSpeech;
    }

    public void setTxtToSpeech(TxtToSpeech txtToSpeech) {
        this.txtToSpeech = txtToSpeech;
    }

    /** Instantiate the interface and set the context */
    public WebAppInterface(Context c) {
        mContext = c;
        txtToSpeech = new TxtToSpeech(c);
        learningPresenter = new LearningPresenter(mContext);
    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        txtToSpeech.speak(toast);
        Log.d(TAG,txtToSpeech.getTextToSpeech().toString());
    }

    @JavascriptInterface
    public void updateScoreIntoDatabase(String currentScoreString, int maxScore){
        ScoreInfo scoreInfo = null;
        int score = 0;
        String previousScoreString;

        ScoreInfo newScoreInfo = null;

        if (unitName != null) {
            scoreInfo = learningPresenter.getScoreInfo(unitName);
        }
        else {
            Log.d(TAG,"unitName null");
        }

        if (scoreInfo != null){
            score = scoreInfo.getScore();
            previousScoreString = scoreInfo.getPreviousScoreString();
            if (previousScoreString == null){
                newScoreInfo = new ScoreInfo(currentScoreString, calculateScore(currentScoreString),maxScore);
                learningPresenter.updateScoreInfo(unitName, newScoreInfo);
            }
            else{
                String[] oldScores = previousScoreString.split(" ");
                String[] newScores = currentScoreString.split(" ");
                String[] newScoreString = new String[oldScores.length];
                for (int i=0; i< oldScores.length; i++){
                    int diff = Integer.parseInt(newScores[i]) - Integer.parseInt(oldScores[i]);
                    if (diff > 0){
                        score += diff;
                        newScoreString[i] = newScores[i];
                    }
                    else{
                        newScoreString[i] = oldScores[i];
                    }
                }
                String newPreviousScoreString = "";
                for (String s : newScoreString){
                    newPreviousScoreString += s + " ";
                }
                newScoreInfo = new ScoreInfo(newPreviousScoreString.trim(), score, maxScore);
                learningPresenter.updateScoreInfo(unitName, newScoreInfo);
            }
        }

    }

    private int calculateScore(String scoreString){
        int score = 0;
        String[] scores = scoreString.split(" ");
        for (String s : scores){
            score += Integer.parseInt(s);
        }
        return score;
    }
}

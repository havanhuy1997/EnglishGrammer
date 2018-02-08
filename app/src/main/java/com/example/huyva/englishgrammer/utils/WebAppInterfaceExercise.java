package com.example.huyva.englishgrammer.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.example.huyva.englishgrammer.activities.learningActivity.LearningPresenter;
import com.example.huyva.englishgrammer.dialogs.ScoreDialog;
import com.example.huyva.englishgrammer.objects.ScoreInfo;

/**
 * Created by huyva on 2/7/2018.
 */

public class WebAppInterfaceExercise {
    static final String TAG = "WebApiInterfaceExercise";
    static private Context mContext;
    private LearningPresenter learningPresenter;
    private String unitName;
    private Activity learningActivity;
    ScoreDialog scoreDialog;

    public Activity getLearningActivity() {
        return learningActivity;
    }

    public void setLearningActivity(Activity learningActivity) {
        this.learningActivity = learningActivity;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    /** Instantiate the interface and set the context */
    public WebAppInterfaceExercise(Context c) {
        mContext = c;
        learningPresenter = new LearningPresenter(mContext);
    }

    @JavascriptInterface
    public void updateScoreIntoDatabase(String currentScoreString, int maxScore){
        //Show Dialog
        scoreDialog = new ScoreDialog();
        scoreDialog.showDialog(learningActivity,"Score: "+calculateScore(currentScoreString) + "/" + maxScore);

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

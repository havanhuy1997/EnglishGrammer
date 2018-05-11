package com.huyha.van.englishgrammer.activities.learningActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.huyha.van.englishgrammer.models.database.Database;
import com.huyha.van.englishgrammer.models.database.DatabaseColume;
import com.huyha.van.englishgrammer.objects.ScoreInfo;

/**
 * Created by huyva on 2/4/2018.
 */

public class LearningPresenter {
    private final String TAG = "LearningPresenter";
    Database database;
    private SQLiteDatabase sqLiteDatabase;
    private Context mContext;

    public LearningPresenter(Context context){
        mContext = context;
        database = new Database();
        database.setmContext(mContext);
        sqLiteDatabase = Database.getInstance();
    }

    public ScoreInfo getScoreInfo(String unitName){
        String sqlString = "SELECT * from unit WHERE unit_name = '"+unitName+"'";
        Cursor cursor = sqLiteDatabase.rawQuery(sqlString,null);
        ScoreInfo scoreInfo = null;
        while (cursor.moveToNext()){
            String previousScoreString = cursor.getString(DatabaseColume.previousScoreColumn);
            int score = cursor.getInt(DatabaseColume.scoreColumn);
            int maxScore = cursor.getInt(DatabaseColume.maxScoreColumn);
            scoreInfo = new ScoreInfo(previousScoreString,score,maxScore);
        }
        return scoreInfo;
    }

    public void updateScoreInfo(String unitName, ScoreInfo scoreInfo){
        ContentValues cv = new ContentValues();
        cv.put(DatabaseColume.previousScoreColumnName, scoreInfo.getPreviousScoreString());
        cv.put(DatabaseColume.maxScoreColumnName, scoreInfo.getMaxScore());
        cv.put(DatabaseColume.scoreColumnName, scoreInfo.getScore());

        int effectRow = sqLiteDatabase.update("unit", cv, DatabaseColume.unitColumnName + "='" + unitName+"'", null);
        if (!(effectRow > 0)){
            Log.d(TAG,"error Update");
        }
    }

}

package com.huyha.van.englishgrammer.activities.unitActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.huyha.van.englishgrammer.models.database.Database;
import com.huyha.van.englishgrammer.models.database.DatabaseColume;
import com.huyha.van.englishgrammer.objects.Unit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huyva on 2/8/2018.
 */

public class UnitPresenter {
    private final String TAG = "UnitPresenter";

    private Context mContext;
    private Database database;
    private SQLiteDatabase sqLiteDatabase;

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public UnitPresenter(Context mContext){
        this.mContext = mContext;
        database = new Database();
        database.setmContext(mContext.getApplicationContext());
        sqLiteDatabase = Database.getInstance();
    }

    /***
     * Gets lists of units from database
     * @param topicName Name of yopic
     * @return lists of unit
     */

    public List<Unit> getUnit(String topicName){
        List<Unit> unitList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * from unit WHERE topic_name = '"+topicName+"'",null);
        while (cursor.moveToNext()){
            String unitName = cursor.getString(DatabaseColume.unitNameColumn);
            String urlGrammer = cursor.getString(DatabaseColume.urlGrammerColumn);
            String urlExercise = cursor.getString(DatabaseColume.urlExerciseColumn);
            Boolean isFavorite = cursor.getInt(DatabaseColume.favoriteUnitColumn) > 0;
            int score = cursor.getInt(DatabaseColume.scoreColumn);
            int maxScore = cursor.getInt(DatabaseColume.maxScoreColumn);
            unitList.add(new Unit(unitName,urlGrammer,urlExercise, isFavorite,score, maxScore));
        }
        return unitList;
    }

    /***
     * Updata to database when users click faovrite button
     * @param unitName name of Unit
     * @param isFavorite
     */

    public void updateFavorite(String unitName, boolean isFavorite){
        ContentValues cv = new ContentValues();
        if (isFavorite){
            cv.put(DatabaseColume.favoriteColumn, 1);
        }else {
            cv.put(DatabaseColume.favoriteColumn, 0);
        }


        int effectRow = sqLiteDatabase.update("unit", cv, DatabaseColume.unitColumnName + "=\"" + unitName+"\"", null);
        if (!(effectRow > 0)){
            Log.d(TAG,"error Update");
        }
    }

    public List<Unit> getFavoriteUnit(){
        List<Unit> unitList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * from unit WHERE " + DatabaseColume.favoriteColumn + " = 1",null);
        while (cursor.moveToNext()){
            String unitName = cursor.getString(DatabaseColume.unitNameColumn);
            String urlGrammer = cursor.getString(DatabaseColume.urlGrammerColumn);
            String urlExercise = cursor.getString(DatabaseColume.urlExerciseColumn);
            Boolean isFavorite = true;
            unitList.add(new Unit(unitName,urlGrammer,urlExercise, isFavorite));
        }
        return unitList;
    }

}

package com.example.huyva.englishgrammer.models.database;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by huyva on 3/12/2018.
 */

public class SharedData {
    private final String KEY_REMIND = "remind";
    private final String KEY_REMIND_HOUR = "remind_hour";
    private final String KEY_REMIND_MINUTE = "remind_minute";
    private final String KEY_VIP = "vip";

    private static SharedData instance = null;
    private SharedPreferences sharedPreferences;
    private SharedData(Context context){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static SharedData getInstance(Context context){
        if (instance == null){
            instance = new SharedData(context);
            return instance;
        }
        return instance;
    }

    public void setRemindTime(int hour, int minute) {
        sharedPreferences.edit()
                .putInt(KEY_REMIND_HOUR, hour)
                .putInt(KEY_REMIND_MINUTE, minute)
                .apply();
    }

    public boolean getRemind() {
        return sharedPreferences.getBoolean(KEY_REMIND, false);
    }

    public int getHour() {
        return sharedPreferences.getInt(KEY_REMIND_HOUR, -1);
    }

    public int getMinute() {
        return sharedPreferences.getInt(KEY_REMIND_MINUTE, -1);
    }

    public void saveRemind(boolean remind) {
        sharedPreferences.edit()
                .putBoolean(KEY_REMIND, remind)
                .apply();
    }

    public void saveVip(){
        sharedPreferences.edit()
                .putBoolean(KEY_VIP, true)
                .apply();
    }

    public boolean getVip(){
        return sharedPreferences.getBoolean(KEY_VIP, false);
    }
}

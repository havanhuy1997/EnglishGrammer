package com.huyha.van.englishgrammer.activities.drawerActivity;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.huyha.van.englishgrammer.R;
import com.huyha.van.englishgrammer.fragments.FavoriteFragment;
import com.huyha.van.englishgrammer.fragments.ProgressFragment;
import com.huyha.van.englishgrammer.fragments.SettingFragment;
import com.huyha.van.englishgrammer.fragments.TopicFragment;
import com.huyha.van.englishgrammer.models.database.Database;

/**
 * Created by huyva on 1/19/2018.
 */

public class DrawerPresenter {
    private static final String TAG = "DrawerPresenter";
    private Activity mContext;
    Database database;
    FragmentManager fragmentManager;

    public DrawerPresenter(Activity activity, FragmentManager fragmentManager){
        this.mContext = activity;
        this.fragmentManager = fragmentManager;
    }


    public void updateDisplay(int position) {
        Fragment fragment = null;
        int grammer = NavParameter.grammer;
        switch (position) {
            case 0:
                TopicFragment topicFragment  = new TopicFragment();
                topicFragment.setmContext(mContext);
                fragment = topicFragment;
                break;
            case 1:
                FavoriteFragment favoriteFragment  = new FavoriteFragment();
                favoriteFragment.setContext(mContext);
                fragment = favoriteFragment;
                break;
            case 2:
                ProgressFragment progressFragment = new ProgressFragment();
                progressFragment.setmContext(mContext);
                fragment = progressFragment;
                break;
            case 3:
                //go to app in Ch play
                final String appPackageName = mContext.getPackageName();
                Log.d(TAG, appPackageName);
                try {
                    mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    mContext.startActivity(new Intent(Intent.ACTION_VIEW,
                                            Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
                break;
            case 4:
                SettingFragment settingFragment = new SettingFragment();
                fragment = settingFragment;
                break;
        }
        changeFragment(fragment);
    }

    private void changeFragment(Fragment fragment){
        if (fragment != null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.transaction_container, fragment).commit();

            // update selected item and title, then close the drawer

        } else {
            // error in creating fragment
            Log.e(TAG, "Error in creating fragment");
        }
    }

    public void createDatabase(){
        try {
            database = new Database();
            database.setmContext(mContext);
            database.createDatabaseDirectory();
            database.copyDatabaseToDirectory();
        }
        catch (Exception e){
            Log.d(TAG,"createDatabase "+e.toString());
        }
    }
}

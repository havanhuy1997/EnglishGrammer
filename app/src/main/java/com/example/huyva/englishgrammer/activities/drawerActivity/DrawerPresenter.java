package com.example.huyva.englishgrammer.activities.drawerActivity;

import android.app.Activity;
import android.app.FragmentManager;
import android.util.Log;
import android.app.Fragment;

import com.example.huyva.englishgrammer.R;
import com.example.huyva.englishgrammer.fragments.FavoriteFragment;
import com.example.huyva.englishgrammer.fragments.ProgressFragment;
import com.example.huyva.englishgrammer.fragments.TopicFragment;
import com.example.huyva.englishgrammer.models.database.Database;

/**
 * Created by huyva on 1/19/2018.
 */

public class DrawerPresenter {
    private static final String TAG = "DrawerPresenter";
    private Activity mContext;
    Database database;

    public DrawerPresenter(Activity activity){
        this.mContext = activity;
    }


    public void updateDisplay(int position) {
        Fragment fragment = null;
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
            default:
                break;
        }
        changeFragment(fragment);
    }

    private void changeFragment(Fragment fragment){
        if (fragment != null) {
            FragmentManager fragmentManager = mContext.getFragmentManager();
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

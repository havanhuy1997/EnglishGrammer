package com.example.huyva.englishgrammer.activities.drawerActivity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.util.Log;

import com.example.huyva.englishgrammer.R;
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
        TopicFragment fragment = null;
        switch (position) {
            case 0:
                fragment  = new TopicFragment();
                fragment.setmContext(mContext);
                changeFragment(fragment);
                break;
            case 1:
                break;
            case 2:
                break;
            default:
                break;
        }
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

package com.example.huyva.englishgrammer.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huyva on 1/26/2018.
 */

public class PageAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private List<String> names;


    public PageAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
        names = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return names.get(position);
    }

    public void addFragment(Fragment fragment, String name) {
        fragments.add(fragment);
        names.add(name);
    }

}

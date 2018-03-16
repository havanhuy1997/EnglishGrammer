package com.example.huyva.englishgrammer.activities.drawerActivity;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.huyva.englishgrammer.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerPresenter drawerPresenter;
    TextView txtEmail;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.ad_layout_drawer)
    FrameLayout adLayoutDrawer;
    AdView adView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        ButterKnife.bind(this);
        init();
        initAd();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_grammer) {
            drawerPresenter.updateDisplay(NavParameter.grammer);
        } else if (id == R.id.nav_favorite_unit) {
            drawerPresenter.updateDisplay(NavParameter.favoriteUnit);
        } else if (id == R.id.nav_progress) {
            drawerPresenter.updateDisplay(NavParameter.progress);
        } else if (id == R.id.nav_app_view) {
            drawerPresenter.updateDisplay(NavParameter.appView);
        } else if (id == R.id.nav_setting){
            drawerPresenter.updateDisplay(NavParameter.setting);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        drawerPresenter.database = null;
        drawerPresenter = null;
    }

    void init(){
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        drawerPresenter = new DrawerPresenter(this, getSupportFragmentManager());
        drawerPresenter.updateDisplay(0);
        drawerPresenter.createDatabase();
    }
    void initAd(){
        MobileAds.initialize(this,getString(R.string.app_id));
        adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(getString(R.string.banner));

        final Handler handler = new Handler();
        final Runnable run = new Runnable() {
            @Override
            public void run() {
                AdRequest adRequest = new AdRequest.Builder().build();
                adView.loadAd(adRequest);
            }
        };
        handler.postDelayed(run, 1000);

        adView.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {
                Log.d("AD", "closed");
                super.onAdClosed();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                adLayoutDrawer.removeAllViews();
                super.onAdFailedToLoad(i);
                Log.d("AD", "failtoload");
            }

            @Override
            public void onAdLeftApplication() {
                Log.d("AD", "leftapp");
                super.onAdLeftApplication();
            }

            @Override
            public void onAdOpened() {
                Log.d("AD", "opened");
                super.onAdOpened();
            }

            @Override
            public void onAdLoaded() {
                adLayoutDrawer.removeAllViews();
                adLayoutDrawer.addView(adView);
                Log.d("AD", "loaded");
                super.onAdLoaded();
            }
        });
    }
}

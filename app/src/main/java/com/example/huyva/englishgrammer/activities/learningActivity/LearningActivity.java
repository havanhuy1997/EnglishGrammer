package com.example.huyva.englishgrammer.activities.learningActivity;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;

import com.example.huyva.englishgrammer.R;
import com.example.huyva.englishgrammer.adapters.PageAdapter;
import com.example.huyva.englishgrammer.fragments.ExerciseFragment;
import com.example.huyva.englishgrammer.fragments.GrammerFragment;
import com.example.huyva.englishgrammer.objects.Unit;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LearningActivity extends AppCompatActivity {
    private String TAG = "LearnActivity";
    @BindView(R.id.vp_main_pager)
    ViewPager mVpPager;
    @BindView(R.id.tl_main_tab)
    TabLayout mTlMainTab;
    AdView adView;
    @BindView(R.id.ad_layout_learning)
    FrameLayout adLayout;
    InterstitialAd mInterstitialAd;

    private GrammerFragment grammerFragment;
    private ExerciseFragment exerciseFragment;
    private final String dirPath = "file:///android_asset/unit/";
    String urlGrammer;
    String urlExercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);
        ButterKnife.bind(this);
        Log.d(TAG,"onCreate");

        init();
        initAd();
        initAdInterstitial();
    }

    private void init(){
        Unit unit =(Unit) getIntent().getSerializableExtra("unit");

        // for grammer fragment
        grammerFragment = new GrammerFragment();
        grammerFragment.setmContext(getApplicationContext());
        urlGrammer = unit.getUrlGrammer();
        if (urlGrammer != null) {
            grammerFragment.setUrlGrammer(dirPath + urlGrammer);
        }
        else{
            Log.d(TAG,"null urlGrammer");
        }

        //for exercise fragment
        exerciseFragment = new ExerciseFragment();
        exerciseFragment.setLearningActivity(this);
        exerciseFragment.setmContext(getApplicationContext());
        urlExercise = unit.getUrlExercise();
        if (urlExercise != null) {
            exerciseFragment.setUrlExercise(dirPath + urlExercise);
        }
        else{
            Log.d(TAG,"null urlExercise");
        }
        exerciseFragment.setUnitName(unit.getNameUnit());

        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager());
        pageAdapter.addFragment(grammerFragment, "Grammer");
        pageAdapter.addFragment(exerciseFragment,"Exercise");
        mVpPager.setAdapter(pageAdapter);

        mTlMainTab.setupWithViewPager(mVpPager);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
        grammerFragment.setmContext(null);
        exerciseFragment.setmContext(null);
        exerciseFragment.setLearningActivity(null);
    }

    @Override
    public void onBackPressed() {
        if (mInterstitialAd.isLoaded()){
            mInterstitialAd.show();
        }
        else{
            mInterstitialAd = null;
            super.onBackPressed();
        }
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
        handler.postDelayed(run, 500);

        adView.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {
                Log.d("AD", "closed");
                super.onAdClosed();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                adLayout.removeAllViews();
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
                adLayout.removeAllViews();
                adLayout.addView(adView);
                Log.d("AD", "loaded");
                super.onAdLoaded();
            }
        });
    }

    void initAdInterstitial(){
        mInterstitialAd = new InterstitialAd(getApplicationContext());
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }
}

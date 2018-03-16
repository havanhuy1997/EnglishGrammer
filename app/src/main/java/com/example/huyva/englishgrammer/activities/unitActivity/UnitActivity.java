package com.example.huyva.englishgrammer.activities.unitActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.FrameLayout;

import com.example.huyva.englishgrammer.R;
import com.example.huyva.englishgrammer.activities.learningActivity.LearningActivity;
import com.example.huyva.englishgrammer.adapters.UnitAdapter;
import com.example.huyva.englishgrammer.objects.Topic;
import com.example.huyva.englishgrammer.objects.Unit;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UnitActivity extends AppCompatActivity {
    private static String TAG = "UnitActivity";
    @BindView(R.id.rvUnit)
    RecyclerView rvUnit;
    AdView adView;
    @BindView(R.id.ad_layout_unit)
    FrameLayout adLayout;

    Context context =this;
    List<Unit> unitList = new ArrayList<>();
    UnitAdapter unitAdapter;
    UnitAdapter.OnItemClickListener listener;
    UnitPresenter unitPresenter;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
        listener = null;
        rvUnit = null;
        context = null;
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop");
        unitPresenter.setmContext(null);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit);
        ButterKnife.bind(this);
        initAd();
        Log.d(TAG,"onCreate");

        final Topic topic = (Topic) getIntent().getSerializableExtra("topic");
        if (topic != null){
            Log.d(TAG,topic.getNameTopic());
        }

        unitPresenter = new UnitPresenter(this);
        unitList = unitPresenter.getUnit(topic.getNameTopic());

        if (unitList.size() != 0){
            listener = new UnitAdapter.OnItemClickListener() {
                @Override
                public void onUnitClick(Unit unit) {
                    Log.d(TAG,"OnUnitClick");
                    Intent i = new Intent(context, LearningActivity.class);
                    i.putExtra("unit",unit);
                    startActivity(i);
                }

                @Override
                public void onFavoriteClick(Unit unit) {
                   unit.setFavorite(!unit.isFavorite());
                   unitPresenter.updateFavorite(unit.getNameUnit(), unit.isFavorite());
                   unitList.clear();
                   unitList.addAll(unitPresenter.getUnit(topic.getNameTopic()));
                   unitAdapter.notifyDataSetChanged();
                }
            };
            unitAdapter = new UnitAdapter(unitList, listener);
            rvUnit.setLayoutManager(new LinearLayoutManager(this));
            rvUnit.setAdapter(unitAdapter);
        }
        else{
            Log.d(TAG,"o");
        }

        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle(topic.getNameTopic());
    }

    void initAd(){
        MobileAds.initialize(this,getString(R.string.app_id));
        adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(getString(R.string.banner));
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
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
}

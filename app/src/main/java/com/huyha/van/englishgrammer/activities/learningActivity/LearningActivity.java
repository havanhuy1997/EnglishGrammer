package com.huyha.van.englishgrammer.activities.learningActivity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.huyha.van.englishgrammer.R;
import com.huyha.van.englishgrammer.adapters.PageAdapter;
import com.huyha.van.englishgrammer.fragments.ExerciseFragment;
import com.huyha.van.englishgrammer.fragments.GrammerFragment;
import com.huyha.van.englishgrammer.objects.Unit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LearningActivity extends AppCompatActivity {
    private String TAG = "LearnActivity";
    @BindView(R.id.vp_main_pager)
    ViewPager mVpPager;
    @BindView(R.id.tl_main_tab)
    TabLayout mTlMainTab;

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
        pageAdapter.addFragment(grammerFragment, "Grammar");
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
}

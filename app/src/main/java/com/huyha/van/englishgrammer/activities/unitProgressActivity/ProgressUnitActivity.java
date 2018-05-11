package com.huyha.van.englishgrammer.activities.unitProgressActivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.huyha.van.englishgrammer.R;
import com.huyha.van.englishgrammer.activities.unitActivity.UnitPresenter;
import com.huyha.van.englishgrammer.adapters.UnitProgressAdapter;
import com.huyha.van.englishgrammer.objects.Topic;
import com.huyha.van.englishgrammer.objects.Unit;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProgressUnitActivity extends AppCompatActivity {
    private static String TAG = "UnitProgressActivity";
    @BindView(R.id.rvUnitProgress)
    RecyclerView rvUnitProgress;

    Context context =this;
    List<Unit> unitList = new ArrayList<>();
    UnitProgressAdapter unitProgressAdapter;
    UnitProgressAdapter.OnItemClickListener listener;
    UnitPresenter unitPresenter;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
        listener = null;
        rvUnitProgress = null;
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
        setContentView(R.layout.activity_progress_unit);
        ButterKnife.bind(this);
        Log.d(TAG,"onCreate");

        final Topic topic = (Topic) getIntent().getSerializableExtra("topic");
        if (topic != null){
            Log.d(TAG,topic.getNameTopic());
        }

        unitPresenter = new UnitPresenter(this);
        unitList = unitPresenter.getUnit(topic.getNameTopic());

        if (unitList.size() != 0){
            listener = new UnitProgressAdapter.OnItemClickListener() {
                @Override
                public void onUnitClick(Unit unit) {

                }
            };
            unitProgressAdapter = new UnitProgressAdapter(context, unitList, listener);
            rvUnitProgress.setLayoutManager(new LinearLayoutManager(this));
            rvUnitProgress.setAdapter(unitProgressAdapter);
        }
        else{
            Log.d(TAG,"o");
        }

        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle(topic.getNameTopic());
    }
}

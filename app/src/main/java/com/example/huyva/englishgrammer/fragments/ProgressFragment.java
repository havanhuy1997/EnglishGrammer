package com.example.huyva.englishgrammer.fragments;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.huyva.englishgrammer.R;
import com.example.huyva.englishgrammer.activities.unitActivity.UnitPresenter;
import com.example.huyva.englishgrammer.activities.unitProgressActivity.ProgressUnitActivity;
import com.example.huyva.englishgrammer.adapters.TopicProgressAdapter;
import com.example.huyva.englishgrammer.objects.Topic;
import com.example.huyva.englishgrammer.objects.Unit;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by huyva on 2/9/2018.
 */

public class ProgressFragment extends Fragment {
    private final String TAG = "ProgressFragment";

    @BindView(R.id.rvProgressTopic)
    RecyclerView rvProgressTopic;

    List<Topic> topicList;
    List<Integer> scoreList;
    List<Integer> maxScoreList;

    TopicProgressAdapter topicProgressAdapter;
    TopicProgressAdapter.OnItemClickListener listener;
    private Context mContext;
    UnitPresenter unitPresenter;

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
        unitPresenter = new UnitPresenter(mContext);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_progress, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        topicList = new ArrayList<>();
        scoreList = new ArrayList<>();
        maxScoreList = new ArrayList<>();
        String[] topicName = getResources().getStringArray(R.array.topic_name);
        for (String s : topicName){
            topicList.add(new Topic(s));
            List<Unit> unitList = unitPresenter.getUnit(s);
            int maxScore = 0;
            int score = 0;
            int percent = 0;
            for (Unit u : unitList){
                score += u.getScore();
                maxScore += u.getMaxScore();
            }
            scoreList.add(score);
            maxScoreList.add(maxScore);
        }
        listener = new TopicProgressAdapter.OnItemClickListener() {
            @Override
            public void onTopicClick(Topic topic) {
                Intent intent = new Intent(mContext, ProgressUnitActivity.class);
                intent.putExtra("topic",topic);
                mContext.startActivity(intent);
            }
        };
        topicProgressAdapter = new TopicProgressAdapter(topicList, scoreList, maxScoreList, listener, mContext);
        rvProgressTopic.setLayoutManager(new LinearLayoutManager(mContext));
        rvProgressTopic.setAdapter(topicProgressAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mContext = null;
        unitPresenter.setmContext(null);
    }
}

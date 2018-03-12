package com.example.huyva.englishgrammer.fragments;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.huyva.englishgrammer.R;
import com.example.huyva.englishgrammer.activities.unitActivity.UnitActivity;
import com.example.huyva.englishgrammer.adapters.TopicAdapter;
import com.example.huyva.englishgrammer.objects.Topic;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by huyva on 1/19/2018.
 */

public class TopicFragment extends Fragment {
    private final String TAG = "TopicFragment";

    @BindView(R.id.rvTopic)
    RecyclerView rvTopic;
    List<Topic> topicList;
    TopicAdapter topicAdapter;
    TopicAdapter.OnItemClickListener listener;
    private Context mContext;

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_topic, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        topicList = new ArrayList<>();
        String[] topicName = getResources().getStringArray(R.array.topic_name);
        for (String s : topicName){
            topicList.add(new Topic(s));
        }
        listener = new TopicAdapter.OnItemClickListener() {
            @Override
            public void onTopicClick(Topic topic) {
                Log.d(TAG,"Onclick");
                Intent intent = new Intent(mContext, UnitActivity.class);
                intent.putExtra("topic",topic);
                startActivity(intent);
            }
        };
        topicAdapter = new TopicAdapter(topicList, listener);
        rvTopic.setLayoutManager(new LinearLayoutManager(mContext));
        rvTopic.setAdapter(topicAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mContext = null;
    }
}

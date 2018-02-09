package com.example.huyva.englishgrammer.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.huyva.englishgrammer.R;
import com.example.huyva.englishgrammer.activities.unitActivity.UnitPresenter;
import com.example.huyva.englishgrammer.objects.Topic;
import com.example.huyva.englishgrammer.objects.Unit;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by huyva on 2/9/2018.
 */

public class TopicProgressAdapter extends RecyclerView.Adapter<TopicProgressAdapter.TopicProgressHolder> {
    private final String TAG = "TopicProgressAdapter";

    private UnitPresenter unitPresenter;
    private Context mContext;
    List<Topic> topicList;
    private TopicProgressAdapter.OnItemClickListener listener;

    public TopicProgressAdapter.OnItemClickListener getListener() {
        return listener;
    }

    public void setListener(TopicProgressAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    public TopicProgressAdapter(List<Topic> topicList, TopicProgressAdapter.OnItemClickListener listener, Context context){
        this.mContext = context;
        this.topicList = topicList;
        this.listener = listener;
        unitPresenter = new UnitPresenter(context);
    }

    @Override
    public TopicProgressAdapter.TopicProgressHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_topic_progress,parent,false);
        return new TopicProgressAdapter.TopicProgressHolder(v);
    }

    @Override
    public void onBindViewHolder(TopicProgressAdapter.TopicProgressHolder holder, int position) {
        holder.bind(position,listener);
    }

    @Override
    public int getItemCount() {
        return topicList.size();
    }

    class TopicProgressHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.txtTopicProgress)
        TextView txtTopicProgress;
        @BindView(R.id.txtPercentTopic)
        TextView txtPercentTopic;
        @BindView(R.id.pbTopic)
        ProgressBar pbTopic;

        public TopicProgressHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        void bind(int position, final TopicProgressAdapter.OnItemClickListener listener){
            final Topic topic = topicList.get(position);
            txtTopicProgress.setText(topic.getNameTopic());

            List<Unit> unitList = new ArrayList<>();
            unitList = unitPresenter.getUnit(topic.getNameTopic());
            int maxScore = 0;
            int score = 0;
            int percent = 0;
            for (Unit u : unitList){
                score += u.getScore();
                maxScore += u.getMaxScore();
            }
            if (maxScore != 0){
                percent = (score / maxScore) * 100;
            }

            txtPercentTopic.setText(50+"%");
            pbTopic.setProgress(50);

            pbTopic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onTopicClick(topic);
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onTopicClick(Topic topic);
    }
}

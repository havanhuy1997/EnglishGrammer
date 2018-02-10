package com.example.huyva.englishgrammer.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.huyva.englishgrammer.R;
import com.example.huyva.englishgrammer.objects.Topic;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by huyva on 2/9/2018.
 */

public class TopicProgressAdapter extends RecyclerView.Adapter<TopicProgressAdapter.TopicProgressHolder> {
    private final String TAG = "TopicProgressAdapter";

    private Context mContext;
    List<Topic> topicList;
    List<Integer> scoreList;
    List<Integer> maxScoreList;
    private TopicProgressAdapter.OnItemClickListener listener;

    public TopicProgressAdapter.OnItemClickListener getListener() {
        return listener;
    }

    public void setListener(TopicProgressAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    public TopicProgressAdapter(List<Topic> topicList, List<Integer> scoreList, List<Integer> maxScoreList, TopicProgressAdapter.OnItemClickListener listener, Context context){
        this.mContext = context;
        this.topicList = topicList;
        this.maxScoreList = maxScoreList;
        this.scoreList = scoreList;
        this.listener = listener;
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
            final int score = scoreList.get(position);
            final int maxScore = maxScoreList.get(position);
            txtTopicProgress.setText(topic.getNameTopic());
            int percent = 0;
            if (maxScore != 0){
                percent = score *100 / maxScore;
            }

            txtPercentTopic.setText(percent+"%");
            pbTopic.setProgress(percent);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (maxScore != 0) {
                        listener.onTopicClick(topic);
                    }
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onTopicClick(Topic topic);
    }
}

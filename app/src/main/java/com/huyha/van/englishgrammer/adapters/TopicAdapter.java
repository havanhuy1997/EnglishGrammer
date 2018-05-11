package com.huyha.van.englishgrammer.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huyha.van.englishgrammer.R;
import com.huyha.van.englishgrammer.objects.Topic;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by huyva on 1/19/2018.
 */

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicHolder> {
    private final String TAG = "TopicAdapter";
    List<Topic> topicList;
    private OnItemClickListener listener;

    public OnItemClickListener getListener() {
        return listener;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public TopicAdapter(List<Topic> topicList, OnItemClickListener listener){
        this.topicList = topicList;
        this.listener = listener;
    }

    @Override
    public TopicHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_topic,parent,false);
        return new TopicHolder(v);
    }

    @Override
    public void onBindViewHolder(TopicHolder holder, int position) {
        holder.bind(position,listener);
    }

    @Override
    public int getItemCount() {
        return topicList.size();
    }

    class TopicHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.txtTopic)
        TextView txtTopic;
        public TopicHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        void bind(int position, final OnItemClickListener listener){
            final Topic topic = topicList.get(position);
            txtTopic.setText(topic.getNameTopic());
            txtTopic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onTopicClick(topic);
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onTopicClick(Topic topic);
    }
}

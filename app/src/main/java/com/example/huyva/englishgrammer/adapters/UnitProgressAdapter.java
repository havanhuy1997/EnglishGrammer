package com.example.huyva.englishgrammer.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.huyva.englishgrammer.R;
import com.example.huyva.englishgrammer.objects.Unit;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by huyva on 2/10/2018.
 */

public class UnitProgressAdapter extends RecyclerView.Adapter<UnitProgressAdapter.UnitProgressHolder> {
    private final String TAG = "UnitProgressAdapter";

    private Context mContext;
    List<Unit> unitList;
    private UnitProgressAdapter.OnItemClickListener listener;

    public UnitProgressAdapter(Context mContext, List<Unit> unitList, OnItemClickListener listener) {
        this.mContext = mContext;
        this.unitList = unitList;
        this.listener = listener;
    }

    @Override
    public UnitProgressHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_unit_progress,parent,false);
        return new UnitProgressHolder(v);
    }

    @Override
    public void onBindViewHolder(UnitProgressHolder holder, int position) {
        holder.bind(position, listener);
    }

    @Override
    public int getItemCount() {
        return unitList.size();
    }

    class UnitProgressHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.txtUnitProgress)
        TextView txtUnitProgress;
        @BindView(R.id.txtPercentUnit)
        TextView txtPercentUnit;
        @BindView(R.id.pbUnit)
        com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar pbUnit;

        public UnitProgressHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(int position, final UnitProgressAdapter.OnItemClickListener listener){
            final Unit unit = unitList.get(position);
            txtUnitProgress.setText(unit.getNameUnit());
            int percent = 0;
            if (unit.getMaxScore() != 0){
                percent = unit.getScore() * 100 / unit.getMaxScore();
            }

            txtPercentUnit.setText(percent+"%");
            pbUnit.setProgress(percent);

            pbUnit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onUnitClick(unit);
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onUnitClick(Unit unit);
    }
}

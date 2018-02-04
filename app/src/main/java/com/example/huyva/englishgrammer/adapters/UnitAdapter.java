package com.example.huyva.englishgrammer.adapters;

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
 * Created by huyva on 1/21/2018.
 */

public class UnitAdapter extends RecyclerView.Adapter<UnitAdapter.UnitHolder> {
    private final String TAG = "UnitAdapter";
    List<Unit> unitList;
    private OnItemClickListener listener;

    public OnItemClickListener getListener() {
        return listener;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public UnitAdapter(List<Unit> unitList, OnItemClickListener listener){
        this.unitList = unitList;
        this.listener = listener;
    }

    @Override
    public UnitHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_unit,parent,false);
        return new UnitHolder(v);
    }

    @Override
    public void onBindViewHolder(UnitHolder holder, int position) {
        holder.bind(position, listener);
    }

    @Override
    public int getItemCount() {
        return unitList.size();
    }


    class UnitHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.txtUnit)
        TextView txtUnit;
        public UnitHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        void bind(int position, final OnItemClickListener listener){
            final Unit unit = unitList.get(position);
            txtUnit.setText(unit.getNameUnit());
            txtUnit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onUnitClick(unit);
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onUnitClick(Unit unit);
    }
}
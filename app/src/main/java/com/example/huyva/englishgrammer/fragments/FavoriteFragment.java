package com.example.huyva.englishgrammer.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.huyva.englishgrammer.R;
import com.example.huyva.englishgrammer.activities.learningActivity.LearningActivity;
import com.example.huyva.englishgrammer.activities.unitActivity.UnitPresenter;
import com.example.huyva.englishgrammer.adapters.UnitAdapter;
import com.example.huyva.englishgrammer.objects.Unit;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by huyva on 2/8/2018.
 */

public class FavoriteFragment extends Fragment {
    private String TAG = "FavoriteFragment";

    @BindView(R.id.rvFavoriteUnit)
    RecyclerView rvFavoriteUnit;
    @BindView(R.id.txtEmptyFavorite)
    TextView txtEmptyFavorite;

    private Context context;
    List<Unit> unitList = new ArrayList<>();
    UnitAdapter unitAdapter;
    UnitAdapter.OnItemClickListener listener;
    UnitPresenter unitPresenter;

    @Override
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_favorite, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart");
        super.onStart();

        unitPresenter = new UnitPresenter(context);
        unitList = unitPresenter.getFavoriteUnit();
        if (unitList.size() != 0) {
            //hide txtEmptyFovorite
            txtEmptyFavorite.setVisibility(View.INVISIBLE);
            listener = new UnitAdapter.OnItemClickListener() {
                @Override
                public void onUnitClick(Unit unit) {
                    Log.d(TAG, "OnUnitClick");
                    Intent i = new Intent(context, LearningActivity.class);
                    i.putExtra("unit", unit);
                    startActivity(i);
                }

                @Override
                public void onFavoriteClick(Unit unit) {
                    unit.setFavorite(!unit.isFavorite());
                    unitPresenter.updateFavorite(unit.getNameUnit(), unit.isFavorite());
                    unitList.clear();
                    unitList.addAll(unitPresenter.getFavoriteUnit());
                    unitAdapter.notifyDataSetChanged();
                }
            };
            unitAdapter = new UnitAdapter(unitList, listener);
            rvFavoriteUnit.setLayoutManager(new LinearLayoutManager(context));
            rvFavoriteUnit.setAdapter(unitAdapter);
        } else {
            txtEmptyFavorite.setVisibility(View.VISIBLE);
            Log.d(TAG, "o");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        unitPresenter.setmContext(null);
    }
}

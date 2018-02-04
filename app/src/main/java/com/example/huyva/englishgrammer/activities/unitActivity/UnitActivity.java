package com.example.huyva.englishgrammer.activities.unitActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.huyva.englishgrammer.R;
import com.example.huyva.englishgrammer.activities.learningActivity.LearningActivity;
import com.example.huyva.englishgrammer.adapters.UnitAdapter;
import com.example.huyva.englishgrammer.models.database.Database;
import com.example.huyva.englishgrammer.models.database.DatabaseColume;
import com.example.huyva.englishgrammer.objects.Topic;
import com.example.huyva.englishgrammer.objects.Unit;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UnitActivity extends AppCompatActivity {
    private static String TAG = "UnitActivity";
    @BindView(R.id.rvUnit)
    RecyclerView rvUnit;

    Context context =this;
    List<Unit> unitList = new ArrayList<>();
    UnitAdapter unitAdapter;
    Database database;
    SQLiteDatabase sqLiteDatabase;
    UnitAdapter.OnItemClickListener listener;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
        listener = null;
        rvUnit = null;
        context = null;
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop");
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit);
        ButterKnife.bind(this);
        Log.d(TAG,"onCreate");

        Topic topic = (Topic) getIntent().getSerializableExtra("topic");
        if (topic != null){
            Log.d(TAG,topic.getNameTopic());
        }
        database = new Database();
        database.setmContext(getApplicationContext());
        sqLiteDatabase = Database.getInstance();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * from unit WHERE topic_name = '"+topic.getNameTopic()+"'",null);
        while (cursor.moveToNext()){
           String unitName = cursor.getString(DatabaseColume.unitNameColumn);
            String urlGrammer = cursor.getString(DatabaseColume.urlGrammerColumn);
            String urlExercise = cursor.getString(DatabaseColume.urlExerciseColumn);
            unitList.add(new Unit(unitName,urlGrammer,urlExercise));
        }
        if (unitList.size() != 0){
            listener = new UnitAdapter.OnItemClickListener() {
                @Override
                public void onUnitClick(Unit unit) {
                    Log.d(TAG,"OnUnitClick");
                    Intent i = new Intent(context, LearningActivity.class);
                    i.putExtra("unit",unit);
                    startActivity(i);
                }
            };
            unitAdapter = new UnitAdapter(unitList, listener);
            rvUnit.setLayoutManager(new LinearLayoutManager(this));
            rvUnit.setAdapter(unitAdapter);
        }
        else{
            Log.d(TAG,"o");
        }

        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle(topic.getNameTopic());
    }
}

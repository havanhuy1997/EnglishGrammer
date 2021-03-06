package com.huyha.van.englishgrammer.fragments;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.huyha.van.englishgrammer.R;
import com.huyha.van.englishgrammer.utils.WebAppInterfaceExercise;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ExerciseFragment extends Fragment {
    private String TAG = "ExerciseFragment";
    @BindView(R.id.wvExercise)
    WebView wvExercise;

    private Context mContext;
    private String urlExercise;
    private String unitName;
    private Activity learningActivity;
    private WebAppInterfaceExercise webAppInterface;

    public Activity getLearningActivity() {
        return learningActivity;
    }

    public void setLearningActivity(Activity learningActivity) {
        this.learningActivity = learningActivity;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUrlExercise() {
        return urlExercise;
    }

    public void setUrlExercise(String urlExercise) {
        this.urlExercise = urlExercise;
    }

    public ExerciseFragment() {
        // Required empty public constructor
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
        View v = inflater.inflate(R.layout.fragment_exercise, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onStart() {
        super.onStart();
        wvExercise.setWebViewClient(new WebViewClient(){
            ProgressDialogFragment progressDialogFragment = new ProgressDialogFragment("Loading");
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                FragmentManager fm = getActivity().getFragmentManager();
                progressDialogFragment.show(fm,"sdf");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressDialogFragment.dismissAllowingStateLoss();
            }
        });
        if (mContext != null) {
            webAppInterface = new WebAppInterfaceExercise(mContext);
            webAppInterface .setLearningActivity(learningActivity);
            webAppInterface.setUnitName(unitName);
            wvExercise.addJavascriptInterface(webAppInterface,"AndroidExercise");
            WebSettings webSettings = wvExercise.getSettings();
            webSettings.setJavaScriptEnabled(true);
        }
        else{
            Log.d(TAG,"context null");
        }
        if (urlExercise != null){
            Log.d(TAG, String.valueOf(wvExercise.getSettings().getJavaScriptEnabled()));
            wvExercise.loadUrl(urlExercise);
        }
        else {
            Log.d(TAG, "urlExercise null");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        webAppInterface.setmContext(null);
        webAppInterface.setLearningActivity(null);
    }
}


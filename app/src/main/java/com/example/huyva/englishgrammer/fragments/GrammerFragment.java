package com.example.huyva.englishgrammer.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.huyva.englishgrammer.R;
import com.example.huyva.englishgrammer.utils.WebAppInterface;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GrammerFragment extends Fragment {
    static private String TAG = "GrammerFragment";
    @BindView(R.id.wvGrammer)
    WebView wvGrammer;
    private WebAppInterface webAppInterface;
    private Context mContext;
    private String urlGrammer;

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public String getUrlGrammer() {
        return urlGrammer;
    }

    public void setUrlGrammer(String urlGrammer) {
        this.urlGrammer = urlGrammer;
    }

    public GrammerFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout  for this fragment
        View v = inflater.inflate(R.layout.fragment_grammer, container, false);
        ButterKnife.bind(this,v);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (urlGrammer != null){
            wvGrammer.loadUrl(urlGrammer);
        }
        else{
            Log.d(TAG,"urlGrammer null");
        }
        if (mContext != null) {
            webAppInterface = new WebAppInterface(mContext);
            wvGrammer.addJavascriptInterface(webAppInterface,"Android");
            WebSettings webSettings = wvGrammer.getSettings();
            webSettings.setJavaScriptEnabled(true);
        }
        else{
            Log.d(TAG,"context null");
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG,"onStop");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
        webAppInterface.getTxtToSpeech().pauseSpeech();
        webAppInterface.getTxtToSpeech().destroySpeech();
        webAppInterface.setmContext(null);
    }
}

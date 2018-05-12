package com.huyha.van.englishgrammer.fragments;

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

import butterknife.BindView;
import butterknife.ButterKnife;

public class GrammerFragment extends Fragment {
    static private String TAG = "GrammerFragment";
    @BindView(R.id.wvGrammer)
    WebView wvGrammer;
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
        wvGrammer.setWebViewClient(new WebViewClient(){
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
        if (urlGrammer != null){
            wvGrammer.loadUrl(urlGrammer);
        }
        else{
            Log.d(TAG,"urlGrammer null");
        }
        if (mContext != null) {
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
    }
}

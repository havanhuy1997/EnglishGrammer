package com.huyha.van.englishgrammer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.huyha.van.englishgrammer.utils.WebAppInterface;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.webView)
    WebView webView;
    WebAppInterface webAppInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        webView.loadUrl("file:///android_asset/unit/unit1/exercise/exercise_1.html");
        webAppInterface = new WebAppInterface(this);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.addJavascriptInterface(webAppInterface,"Android");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webAppInterface.getTxtToSpeech().pauseSpeech();
        webAppInterface.getTxtToSpeech().destroySpeech();
        webAppInterface.setmContext(null);
    }
}

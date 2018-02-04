package com.example.huyva.englishgrammer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.huyva.englishgrammer.utils.WebAppInterface;

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
        webView.loadUrl("file:///android_asset/test.html");
        webAppInterface = new WebAppInterface(this);
        webView.addJavascriptInterface(webAppInterface,"Android");
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webAppInterface.getTxtToSpeech().pauseSpeech();
        webAppInterface.getTxtToSpeech().destroySpeech();
        webAppInterface.setmContext(null);
    }
}

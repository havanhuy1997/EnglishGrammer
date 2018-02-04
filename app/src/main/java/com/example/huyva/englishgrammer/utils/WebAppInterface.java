package com.example.huyva.englishgrammer.utils;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Created by huyva on 1/19/2018.
 */

public class WebAppInterface {
    static final String TAG = "WebApiInterface";
    private Context mContext;
    private TxtToSpeech txtToSpeech;

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public TxtToSpeech getTxtToSpeech() {
        return txtToSpeech;
    }

    public void setTxtToSpeech(TxtToSpeech txtToSpeech) {
        this.txtToSpeech = txtToSpeech;
    }

    /** Instantiate the interface and set the context */
    public WebAppInterface(Context c) {
        mContext = c;
        txtToSpeech = new TxtToSpeech(c);
    }

    public WebAppInterface() {
    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        txtToSpeech.speak(toast);
        Log.d(TAG,txtToSpeech.getTextToSpeech().toString());
    }
}

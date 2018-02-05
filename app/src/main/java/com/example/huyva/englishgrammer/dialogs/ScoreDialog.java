package com.example.huyva.englishgrammer.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.huyva.englishgrammer.R;

/**
 * Created by huyva on 2/5/2018.
 */

public class ScoreDialog {
    TextView txtScore;
    Button okButton;

    public void showDialog(Activity activity, String msg) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_score);

        txtScore = dialog.findViewById(R.id.txtScore);
        txtScore.setText(msg);

        okButton = dialog.findViewById(R.id.btnOK);

        okButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}

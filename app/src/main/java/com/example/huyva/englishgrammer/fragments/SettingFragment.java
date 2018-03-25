package com.example.huyva.englishgrammer.fragments;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.huyva.englishgrammer.R;
import com.example.huyva.englishgrammer.models.database.SharedData;
import com.example.huyva.englishgrammer.other.NotificationReceiver;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by huyva on 3/11/2018.
 */

public class SettingFragment extends Fragment {
    private final String TAG = "SettingFragment";
    private final int REQUEST_CODE = 100;
    long time;
    boolean inShare = false;

    @BindView(R.id.switchStudyReminder)
    Switch switchStudyRemainder;
    @BindView(R.id.txtTimeRemind)
    TextView txtTimeremind;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.bind(this,view);
        boolean remind = SharedData.getInstance(getContext()).getRemind();
        int hour = SharedData.getInstance(getContext()).getHour();
        int minute = SharedData.getInstance(getContext()).getMinute();
        if (hour != -1 && minute != -1){
            txtTimeremind.setText(hour + ":" + minute);
        }
        else {
            txtTimeremind.setVisibility(view.INVISIBLE);
        }
        switchStudyRemainder.setChecked(remind);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (inShare) {
            long timeReturn = Calendar.getInstance().getTimeInMillis();
            if ((timeReturn - time) / 1000 > 30) {
                SharedData.getInstance(getContext()).saveVip();
            }
        }
    }

    @OnClick(R.id.view_remind)
    void changeRemainder(){
        final boolean check = !switchStudyRemainder.isChecked();
        if (check) {
            TimePickerDialog dialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                    Log.d(TAG, String.valueOf(getContext()));
                    SharedData.getInstance(getContext()).saveRemind(true);
                    SharedData.getInstance(getContext()).setRemindTime(hour, minute);
                    switchStudyRemainder.setChecked(true);
                    txtTimeremind.setText(hour + ":" + minute);
                    txtTimeremind.setVisibility(View.VISIBLE);
                    Log.i("Time", hour + ":" + minute);
                    enableReminder(hour,minute);
                }
            }, 20, 0, true);
            dialog.setCancelable(false);
            dialog.show();
        } else {
            txtTimeremind.setVisibility(View.INVISIBLE);
            SharedData.getInstance(getContext()).saveRemind(false);
            switchStudyRemainder.setChecked(false);
            disableReminder();
        }
    }

    @OnClick(R.id.share_app)
    void shareApp(){
        time = Calendar.getInstance().getTimeInMillis();
        inShare = true;
        String urlToShare = "https://play.google.com/store/apps/details?id="+getContext().getPackageName();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, urlToShare);

        boolean facebookAppFound = false;
        List<ResolveInfo> matches = getContext().getPackageManager().queryIntentActivities(intent, 0);
        for (ResolveInfo info : matches) {
            if (info.activityInfo.packageName.toLowerCase().startsWith("com.facebook.katana")) {
                intent.setPackage(info.activityInfo.packageName);
                facebookAppFound = true;
                break;
            }
        }
        if (!facebookAppFound) {
            String sharerUrl = "https://www.facebook.com/sharer/sharer.php?u=" + urlToShare;
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
        }
        startActivity(intent);
    }

    private void disableReminder() {
        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        Intent notificationIntent = new Intent(getContext(), NotificationReceiver.class);
        PendingIntent broadcast = PendingIntent.getBroadcast(getContext(), REQUEST_CODE, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        if (alarmManager != null) {
            alarmManager.cancel(broadcast);
        }
    }

    private void enableReminder(int hour, int minute) {
        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        Intent notificationIntent = new Intent(getContext(), NotificationReceiver.class);
        PendingIntent broadcast = PendingIntent.getBroadcast(getContext(), REQUEST_CODE, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        if (alarmManager != null) {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 24 * 60 * 60 * 1000, broadcast);
        }

    }
}

/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2013-10-12 下午3:51:36
 */

package com.example.phonevibrator.receiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.example.phonevibrator.MainActivity;

public class PhoneCallListener extends PhoneStateListener {
    Intent i;

    PendingIntent pi;

    private AlarmManager am;

    private int triggerAtTime;

    private int interval;

    Context context;

    public PhoneCallListener(Context context) {
        this.context = context;
    }

    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        i = new Intent(context, AlarmReceiver.class);
        pi = PendingIntent.getBroadcast(context, 0, i, 0);
        am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        int sec = MainActivity.second;
        triggerAtTime = sec * 1000;
        interval = 60 * 1000;
        switch (state) {
            case TelephonyManager.CALL_STATE_IDLE:// 空闲
                am.cancel(pi);
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:// 接通
                am.setRepeating(AlarmManager.RTC_WAKEUP,
                        System.currentTimeMillis() + triggerAtTime, interval, pi);
                break;
            case TelephonyManager.CALL_STATE_RINGING:
                break;
            default:
                break;
        }
        super.onCallStateChanged(state, incomingNumber);
    }
}

/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2013-10-8 下午5:41:06
 */

package com.example.phonevibrator.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.util.Log;

/**
 * 类功能描述：</br>
 * 
 * @author GAOZP
 * @version 1.0 </p> 修改时间：</br> 修改备注：</br>
 */
public class AlarmReceiver extends BroadcastReceiver {
    Vibrator mVibrator;

    final int VIBRATE_LENGTH = 100;

    @Override
    public void onReceive(Context context, Intent intent) {
        mVibrator = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
        mVibrator.vibrate(VIBRATE_LENGTH);
    }

}

/*
 * Copyright 2012 flyrise. All rights reserved.
 * Create at 2013-10-14 下午9:08:29
 */

package com.example.phonevibrator.receiver;

import com.example.phonevibrator.MainActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

/**
 * 类功能描述：</br>
 * 
 * @author GAOZP
 * @version 1.0 </p> 修改时间：</br> 修改备注：</br>
 */
public class BootCompleteReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        TelephonyManager telManager = (TelephonyManager)context.getSystemService("phone");
        telManager.listen(new PhoneCallListener(context), PhoneStateListener.LISTEN_CALL_STATE);
    }
}

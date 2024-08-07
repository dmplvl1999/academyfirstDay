package com.hackinghell.academyfirstday;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "Duarte here is an SMS -- cognizant");

        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            SmsMessage[] messages = new SmsMessage[pdus.length];
            for (int i = 0; i < pdus.length; i++) {
                messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
            }
            if (messages.length > 0) {
                Log.i(TAG, "Message received: " + messages[0].getMessageBody());
                Log.i(TAG, "Phone number is: " + messages[0].getDisplayOriginatingAddress());
            }
        }

    }
    private static final String TAG = SmsReceiver.class.getSimpleName();
}

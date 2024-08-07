package com.hackinghell.academyfirstday;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class FlightReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("Flight: ", "Broadcast was sent and it works i guess");
    }
}

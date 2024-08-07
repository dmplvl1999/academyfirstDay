package com.hackinghell.academyfirstday;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyService extends Service {
    private static final String TAG = MyService.class.getSimpleName();

    private final LocalBinder localBinder = new LocalBinder();

    public class LocalBinder extends Binder {
        MyService getMyService() {
            return MyService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("new tag", "my service created");
    }

    public int latestScore() {
        return 5;
    }

    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Log.i(TAG, "my service started--" + intent.getStringExtra("url"));
        MediaPlayer player = MediaPlayer.create(this, R.raw.tune);
        player.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "my service was destroyed");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG,"some activity is trying to bind to this service");
        return localBinder;
    }
}

package com.hackinghell.academyfirstday;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hackinghell.academyfirstday.databinding.ActivityMainBinding;
import com.hackinghell.academyfirstday.networking.MarsApiService;


public class MainActivity extends AppCompatActivity {

    private EditText nameEditText;
    private TextView mainTextView;
    private Button button;
    private Button dialerButton;
    public static final String extra_message = "com.hackinghell.academyfirstday.extra.MESSAGE";

    private ActivityMainBinding binding;
    private String TAG = MainActivity.class.getSimpleName();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        nameEditText = (EditText) findViewById(R.id.editTextText);
        nameEditText.setText("");
        mainTextView = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);
        dialerButton = (Button) findViewById(R.id.dialer);

        View.OnClickListener myClicklis = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainTextView.setText("");
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                String myText = nameEditText.getText().toString();
                //mainTextView.append(myText);
                intent.putExtra(extra_message, myText);
                startActivity(intent);

            }
        };
        button.setOnClickListener(myClicklis);


        View.OnClickListener dialer;
        dialer = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:123456789"));
                //intent.setData(Uri.parse("tel:123456789"))
                startActivity(intent);
            }
        };
        dialerButton.setOnClickListener(dialer);
        }

    @Override
    protected void onStart() {
        super.onStart();
        Intent serviceintent = new Intent(this,MyService.class);

        binding.btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.i("debugger", "does the btn work?");
                serviceintent.putExtra("url", "imageurl.com");
                startService(serviceintent);
            }
        });

        binding.btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(serviceintent);
            }
        });

        binding.btnbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(serviceintent,mConnection, BIND_AUTO_CREATE);
                Log.i(TAG, "debug testing");
            }
        });

        binding.btnunbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(mConnection);
            }
        });
    }
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder localBinder) {
            MyService.LocalBinder binder = (MyService.LocalBinder) localBinder;
            MyService myService = binder.getMyService();
            int soccerScore = myService.latestScore();
            Log.i(TAG, "Score is " +soccerScore);
            int sum = myService.add(10,20);
            Log.i(TAG, "sum is " + sum);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // Handle service disconnection if necessary
            Log.i(TAG, "service was disconnected");
        }
    };


    public void setAlarm(View view) {
        createAlarm("cognizantrev", 20, 43);
    }

    private void createAlarm(String message, int hour, int minutes) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                .putExtra(AlarmClock.EXTRA_MINUTES, minutes);
        //if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        //}
    }

    public void otherApp(View view) {
        Intent intent = new Intent("android.cognizant.portugal");
        startActivity(intent);
    }

    public void sendBroadcast(View view) {
        Intent flightintent = new Intent("ihave.flight");
        flightintent.setComponent(new ComponentName("com.hackinghell.academyfirstday", "com.hackinghell.academyfirstday.FlightReceiver"));
        sendBroadcast(flightintent);
        Log.i("btn", "works here");
    }

    public void getMarsPhotos(View view) {
        new Thread(() -> {
            try {
                String listResult = MarsApiService.MarsApi.retrofitService.getPhotos().execute().body();
                Log.i("Response", listResult);
            } catch (Exception e) {
                Log.e("Error", "Request failed", e);
            }
        });
    }


}
package com.hackinghell.academyfirstday;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {

    private TextView textView;
    private Button notificationBtn;

    //private ActivityAnyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //binding = ActivityAnyService.inflate(getLayoutInflater());

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        textView = (TextView) findViewById(R.id.textView2);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.extra_message);
        textView.setText(message);

        notificationBtn = (Button) findViewById(R.id.button5);
    }

    @Override
    protected void onStart() {
        super.onStart();
        notificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotification();
            }
        });
    }

    private void showNotification() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        createNotificationChannel();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "CHANNEL_ID")
                .setSmallIcon(R.drawable.ic_android_black_24dp)
                .setContentTitle("My Test Notification")
                .setContentText("This is to notify myself that notifications are working")
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.notify(321,builder.build());
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is not in the Support Library.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "uber channel name promotions";
            String description = "channel description --- promotions";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("CHANNEL_ID", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this.
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void sendNotification(View view) {

    }

    public void nextscreen(View view) {
        Intent intent = new Intent(HomeActivity.this, JobSchedulers.class);
        //String myText = nameEditText.getText().toString();
        //mainTextView.append(myText);
        //intent.putExtra(extra_message, myText);
        startActivity(intent);
    }
}
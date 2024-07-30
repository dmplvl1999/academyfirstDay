package com.hackinghell.academyfirstday;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {

    private EditText nameEditText;
    private TextView mainTextView;
    private Button button;
    private Button dialerButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        nameEditText = (EditText) findViewById(R.id.editTextText);
        mainTextView = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);
        dialerButton = (Button) findViewById(R.id.dialer);
        int bn = 1;

        View.OnClickListener myClicklis = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String myText = nameEditText.getText().toString();
                mainTextView.append(myText);
                startActivity(new Intent(MainActivity.this, HomeActivity.class));

            }
        };
        button.setOnClickListener(myClicklis);

        View.OnClickListener dialer = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:123456789"));
                //intent.setData(Uri.parse("tel:123456789"))
                startActivity(intent);
            }
        };
        dialerButton.setOnClickListener(dialer);
    }
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
}
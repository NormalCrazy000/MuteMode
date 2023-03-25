package com.example.mutemode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.security.Provider;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button button_serviceOn,button_serviceOff;
    public static AudioManager aManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        button_serviceOn = findViewById(R.id.button_serviceOn);
        button_serviceOff = (Button) findViewById(R.id.button_serviceOff);
        button_serviceOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent serviceIntent = new Intent(getApplicationContext(), ServiceMute.class);
                ContextCompat.startForegroundService(getApplicationContext(), serviceIntent);
            }
        });
        button_serviceOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent serviceIntent = new Intent(getApplicationContext(), ServiceMute.class);

                stopService(serviceIntent);
            }
        });
    }
    }
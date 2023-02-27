package com.example.mutemode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button btnMute;
    TextView textViewCurrentTime;
    AudioManager aManager;

    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        btnMute = findViewById(R.id.btnMute);
        textViewCurrentTime = findViewById(R.id.textViewCurrentTime);
        btnMute.setOnClickListener(view -> {
            if(aManager.getRingerMode()==AudioManager.RINGER_MODE_SILENT){
                aManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
            }else{
                aManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
            }

            calendar = Calendar.getInstance();
            int hour24hrs = calendar.get(Calendar.HOUR_OF_DAY);
            int minutes = calendar.get(Calendar.MINUTE);
            int seconds = calendar.get(Calendar.SECOND);
            textViewCurrentTime.setText(hour24hrs + ":" + minutes +":"+ seconds);
        });
    }
}
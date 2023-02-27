package com.example.mutemode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnMute;
    AudioManager aManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        btnMute = (Button)findViewById(R.id.btnMute);
        btnMute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(aManager.getRingerMode()==AudioManager.RINGER_MODE_SILENT){
                    aManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                }else{
                    aManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                }

            }
        });
    }
}
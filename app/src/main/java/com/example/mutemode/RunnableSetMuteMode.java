package com.example.mutemode;

import android.content.Context;
import android.media.AudioManager;

import androidx.appcompat.app.AppCompatActivity;

/**
 * extends AppCompatActivity is necessary??????
 * This class is used to set modeOFF/ON
 */
public class RunnableSetMuteMode  implements Runnable {
    AudioManager aManager;

    public RunnableSetMuteMode(){
        aManager = MainActivity.aManager;

    }
    @Override
    public void run() {

            try {
                while (true){
                     if(aManager.getRingerMode()== AudioManager.RINGER_MODE_SILENT){
                aManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
            }else{
                aManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
            }


                    System.out.println("OUTPUT 123");
                    Thread.sleep(2000);
                }

            } catch (InterruptedException e) {
               e.printStackTrace();
            }
    }
}

package com.example.mutemode;

import android.content.Context;
import android.media.AudioManager;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * extends AppCompatActivity is necessary??????
 * This class is used to set modeOFF/ON
 */
public class RunnableSetMuteMode  implements Runnable {
    AudioManager aManager;
    boolean setMuteMode;
    public RunnableSetMuteMode(){
        aManager = MainActivity.aManager;
        setMuteMode = false;
    }
    @Override
    public void run() {

            try {
                while (true){
                    //System.out.println(Calendar.getInstance(Locale.ITALY).getTime().getDay());
                    int day = Calendar.getInstance(Locale.ITALY).getTime().getDay();
                    int hour = Calendar.getInstance(Locale.ITALY).getTime().getHours();
                    int minute = Calendar.getInstance(Locale.ITALY).getTime().getMinutes();
                    if((day == 1 && hour==13 && minute>=30 )||(day == 1 && hour>13 && hour <15 ) ||(day==1 && hour==15 && minute==0)){
                        setMuteMode = true;
                    } else if ((day == 2 && hour==9 && minute>=30 )||(day == 2 && hour>9 && hour <11 ) ||(day==2 && hour==11 && minute==0)) {
                        setMuteMode = true;
                    }else if ((day == 2 && hour==11 && minute>=30 )||(day == 2 && hour>11 && hour <13 ) ||(day==2 && hour==13 && minute==0)) {
                        setMuteMode = true;
                    }else if ((day == 3 && hour==13 && minute>=30 )||(day == 3 && hour>13 && hour < 15) ||(day==3 && hour==15 && minute==0)) {
                        setMuteMode = true;
                    }
                    else if ((day == 3 && hour==15 && minute>=30 )||(day == 3 && hour>15 && hour < 18) ||(day==3 && hour==18 && minute==0)) {
                        setMuteMode = true;
                    }else if ((day == 4 && hour==16 && minute>=30 )||(day == 4 && hour>16 && hour < 18) ||(day==4 && hour==18 && minute==0)) {
                        setMuteMode = true;
                    }else{
                        setMuteMode = false;
                    }
                    // This check if phone must be set to mute/unmute mode
                    if (setMuteMode == false &&(aManager.getRingerMode()== AudioManager.RINGER_MODE_SILENT )){
                        aManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                    }else if ((setMuteMode == true) &&(aManager.getRingerMode()== AudioManager.RINGER_MODE_NORMAL )){
                        aManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                    }
                   // System.out.println("OUTPUT 123");
                    Thread.sleep(1000);
                }

            } catch (InterruptedException e) {
               e.printStackTrace();
            }
    }
}

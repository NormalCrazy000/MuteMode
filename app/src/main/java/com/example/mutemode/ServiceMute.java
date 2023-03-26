package com.example.mutemode;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class ServiceMute extends Service {
    public static final String CHANNEL_ID = "ForegroundServiceChannel";
    private Thread threadMuteMode;
    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("test object creation");
        threadMuteMode = new Thread(new RunnableSetMuteMode());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        createNotificationChannel();
        Intent notificationIntent = new Intent(this, MainActivity.class);

        //Create notification
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Mute Mode")
                .setContentText("---------------------")
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentIntent(pendingIntent)
                .build();
        //Start service
        startForeground(1, notification);
        //Start thread to set mute mode
        threadMuteMode.start();
        return START_NOT_STICKY;
    }

    /**
     * This method is called when service is destroyed
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("TEST service del");
        stopThread();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * This method creates channel to run notification
     */
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Foreground Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }
    //This method is called to interrupt thread
    public void stopThread(){
        try {
            threadMuteMode.interrupt();
        }
        catch (RuntimeException e) {
            System.out.println("Exception handled");
        }
    }
}

package com.example.musicplayapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MusicPlayService extends Service {

    public static final String TAG = MusicPlayService.class.getSimpleName();

    private MediaPlayer mMediaPlayer;
    private NotificationManager mNorificationManager;

    static final String REQUEST_CODE = "REQUEST_CODE";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate()");
        // 音楽再生用のMediaPlayerクラスのインスタンスを取得する
        mMediaPlayer = MediaPlayer.create(this, R.raw.sample);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand()");

        Context context = getApplicationContext();
        int requestCode = intent.getIntExtra(REQUEST_CODE, 0);

        // Notificationを作成する
        Notification notification = createNotification(context, requestCode, intent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForeground(1, notification);
        } else {
            mNorificationManager.notify(1, notification);
        }

        // 音楽再生処理
        audioStart();

        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
        if (mMediaPlayer != null) {
            Log.d(TAG, "end of audio");
            audioStop();
        }
        stopSelf();
    }

    private void audioStart() {
        Log.d(TAG, "audioStart()");
        if (mMediaPlayer != null) {
            mMediaPlayer.setLooping(true);
            mMediaPlayer.start();
            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    Log.d(TAG, "end of audio");
                    audioStop();
                    stopSelf();
                }
            });
        }
    }

    private void audioStop() {
        mMediaPlayer.stop();
        mMediaPlayer.reset();
        mMediaPlayer.release();
        mMediaPlayer = null;
    }

    private Notification createNotification(Context context, int requestCode, Intent intent) {
        String title = context.getString(R.string.app_name);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        mNorificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (mNorificationManager != null) {
            Notification.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                String channelId = "default";
                NotificationChannel channel = new NotificationChannel(channelId, title, NotificationManager.IMPORTANCE_DEFAULT);
                mNorificationManager.createNotificationChannel(channel);
                builder = new Notification.Builder(context, channelId);
            } else {
                builder = new Notification.Builder(context);
            }

            Notification notification = builder.setSmallIcon(R.drawable.ic_play)
                    .setSmallIcon(R.drawable.ic_play)
                    .setContentTitle(title)
                    .setContentText("再生中")
                    .setAutoCancel(false)
                    .setContentIntent(pendingIntent)
                    .setWhen(System.currentTimeMillis())
                    .build();
            notification.flags = Notification.FLAG_NO_CLEAR;
            return notification;
        }
        return null;
    }
}

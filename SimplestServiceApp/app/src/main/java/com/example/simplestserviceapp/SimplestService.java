package com.example.simplestserviceapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class SimplestService extends Service {

    private static final String TAG = SimplestService.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate!");
        Log.d(TAG, "初回起動時に呼ばれる処理。サービスの初期化をするところ。");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onSrartCommand!");
        Log.d(TAG, "サービスの起動。起動都度何度でも呼ばれる。");
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy!");
        Log.d(TAG, "サービスの停止。");
    }
}

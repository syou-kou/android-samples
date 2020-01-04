package com.example.simplestserviceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // サービス開始ボタン処理
    public void onStartClick(View view) {
        Intent intent = new Intent(this, SimplestService.class);
        startService(intent);
    }

    // サービス停止ボタン処理
    public void onStopClick(View view) {
        Intent intent = new Intent(this, SimplestService.class);
        stopService(intent);
    }
}

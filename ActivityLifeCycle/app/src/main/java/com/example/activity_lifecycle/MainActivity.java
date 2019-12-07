package com.example.activity_lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "LifeCycleApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {    // 画面表示時に1度だけ実行される
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate!");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart!");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart!");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume!");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy!");
    }
}

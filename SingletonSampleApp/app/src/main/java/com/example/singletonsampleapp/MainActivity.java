package com.example.singletonsampleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 通常のクラスの場合
        Log.d(TAG, "************************************************************************");
        Log.d(TAG, "通常クラスの場合");

        NotSingleton notSingleton1 = new NotSingleton();
        NotSingleton notSingleton2 = new NotSingleton();

        Log.d(TAG, "notSingleton1 == notSingleton2: " + (notSingleton1 == notSingleton2));

        Log.d(TAG, "notSingleton1.setName(sakamoto)");
        notSingleton1.setName("sakamoto");

        Log.d(TAG, "notSingleton2.getName: " + notSingleton2.getName());
        Log.d(TAG, "************************************************************************");

        Log.d(TAG, "");

        // シングルトンクラスの場合
        Log.d(TAG, "************************************************************************");
        Log.d(TAG, "シングルトンクラスの場合");

        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();

        Log.d(TAG, "singleton1 == singleton2: " + (singleton1 == singleton2));

        Log.d(TAG, "singleton1.setName(sakamoto)");
        singleton1.setName("sakamoto");

        Log.d(TAG, "singleton2.getName: " + singleton2.getName());
        Log.d(TAG, "************************************************************************");
    }
}

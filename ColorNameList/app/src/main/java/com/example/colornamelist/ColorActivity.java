package com.example.colornamelist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class ColorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);

        int resId = getIntent().getIntExtra(MainActivity.KEY_COLOR_RES_ID, -1);

        View colorView = findViewById(R.id.color_view);
        colorView.setBackgroundResource(resId);
    }
}

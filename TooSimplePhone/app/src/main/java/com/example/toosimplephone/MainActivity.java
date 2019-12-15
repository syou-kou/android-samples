package com.example.toosimplephone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
// import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button nextPageBtn = findViewById(R.id.next_page_button);
        Button internetBtn = findViewById(R.id.internet_button);
        Button telephoneBtn = findViewById(R.id.telephone_button);

        nextPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO:「次のページ」ボタンをタップした際の処理
                //  Log.d("MainActivity", "{次のページ}をタップしました");
                Intent intent = new Intent(MainActivity.this, NextPageActivity.class);
                startActivity(intent);
            }
        });
        internetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO:「インターネット」ボタンをタップした際の処理
                // Log.d("MainActivity", "{インターネット}をタップしました");
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/?hl=ja"));
                startActivity(intent);
            }
        });
        telephoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO:「電話」ボタンをタップした際の処理
                // Log.d("MainActivity", "{電話}をタップしました");
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0345330293"));
                startActivity(intent);
            }
        });
    }
}

package com.example.asynchronous_execution;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private CountTask mCountTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.text_view);
        Button mStartBtn = findViewById(R.id.button_start);
        Button mClearBtn = findViewById(R.id.button_clear);

        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // スタートボタンタップ処理
                mCountTask = new CountTask();
                mCountTask.setListener(createListener());
                mCountTask.execute(0);
            }
        });

        mClearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // クリアボタンタップ処理
                mCountTask.cancelTask();
                mTextView.setText(String.valueOf(0));
            }
        });
    }

    private CountTask.Listener createListener() {
        return new CountTask.Listener() {
            @Override
            public void onSuccess(int count) {
                // 表示を変える処理
                mTextView.setText(String.valueOf(count));
            }
        };
    }
}

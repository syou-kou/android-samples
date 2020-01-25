package com.example.stopwatchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mDisplayText;
    private SimpleDateFormat mDateFormat = new SimpleDateFormat("mm:ss.SS", Locale.JAPAN);
    private Thread mThread;
    private long mStartTime;
    private long mEndTime;
    private long mTotalTime;
    private boolean isActive = false;

    private Runnable mTimerTask = new Runnable() {
        @Override
        public void run() {
            while (isActive) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    timerStop();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (isActive) {
                            long endTime = System.currentTimeMillis();
                            long diffTime = endTime - mStartTime;

                            mDisplayText.setText(mDateFormat.format(diffTime + mTotalTime));
                            mEndTime = endTime;
                        }
                    }
                });
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDisplayText = findViewById(R.id.display_text);
        mDisplayText.setText(mDateFormat.format(0));

        findViewById(R.id.start_btn).setOnClickListener(this);
        findViewById(R.id.stop_btn).setOnClickListener(this);
        findViewById(R.id.reset_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.start_btn:
                timerStart();
                break;
            case R.id.stop_btn:
                timerStop();
                break;
            case R.id.reset_btn:
                timerReset();
                break;
            default:
        }
    }

    private void timerStart() {
        if (isActive) return;
        isActive = true;
        mThread = new Thread(mTimerTask);
        mThread.start();
        mStartTime = System.currentTimeMillis();
    }

    private void timerStop() {
        if (!isActive) return;
        isActive = false;
        mTotalTime += mEndTime - mStartTime;
        mThread = null;
    }

    private void timerReset() {
        mDisplayText.setText(mDateFormat.format(0));
        mStartTime = System.currentTimeMillis();
        mTotalTime = 0L;
    }
}

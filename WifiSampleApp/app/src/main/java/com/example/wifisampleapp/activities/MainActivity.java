package com.example.wifisampleapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.wifisampleapp.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private WifiManager wifiManager;
    private BroadcastReceiver wifiScanReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context c, Intent intent) {
            boolean success = intent.getBooleanExtra(
                    WifiManager.EXTRA_RESULTS_UPDATED, false);
            if (success) {
                scanSuccess();
            } else {
                // scan failure handling
                scanFailure();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.wifi_list);
        mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1));

        wifiManager = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(wifiScanReceiver);
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        registerReceiver(wifiScanReceiver, intentFilter);

        boolean success = wifiManager.startScan();
        if (!success) {
            // scan failure handling
            scanFailure();
        }
    }

    private void displayList(List<String> dataList) {
        ArrayAdapter adapter = (ArrayAdapter)mListView.getAdapter();
        adapter.clear();
        adapter.addAll(dataList);
        adapter.notifyDataSetChanged();
    }

    private void scanSuccess() {
        Log.d(MainActivity.class.getSimpleName(), "成功しました");
        List<ScanResult> results = wifiManager.getScanResults();
        List<String> dataList = new ArrayList<>();
        for (ScanResult result : results) {
            Log.d(MainActivity.class.getSimpleName(), "結果:SSID/" + result.SSID);
            String ssid = result.SSID;
            if (!ssid.isEmpty()) {
                dataList.add(ssid);
            }
            // タップしたタイミングで書く
//            Bundle args = new Bundle();
//            args.putParcelable("", result);
//            Intent intent = new Intent();
//            intent.putExtra("", args);
//            startActivity(intent);
        }
        displayList(dataList);
    }

    private void scanFailure() {
        Log.d(MainActivity.class.getSimpleName(), "失敗しました");
        List<ScanResult> results = wifiManager.getScanResults();
    }
}

package com.example.networkconnectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    private static final String DOWNLOAD_URL = "https://www.itpm-gk.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView titleView = findViewById(R.id.title_text);
        titleView.setText(DOWNLOAD_URL);

        findViewById(R.id.download_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new HttpConnectionTask().execute(DOWNLOAD_URL);
            }
        });
    }

    private class HttpConnectionTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            // 非同期処理
            String result = "";
            try {
                result = getDownloadText(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            TextView textView = findViewById(R.id.text_view);
            textView.setText(s);
        }

        private String getDownloadText(String url) throws IOException {
            final int CONNECTION_TIMEOUT = 30 * 1000;
            final int READ_TIMEOUT = 30 * 1000;

            HttpURLConnection conn = (HttpURLConnection)new URL(url).openConnection();
            conn.setConnectTimeout(CONNECTION_TIMEOUT);
            conn.setReadTimeout(READ_TIMEOUT);

            conn.setRequestMethod("GET");
            conn.connect();
            int statusCode = conn.getResponseCode();

            if (statusCode == HttpURLConnection.HTTP_OK) {
                StringBuilder result = new StringBuilder();
                final InputStream in = conn.getInputStream();
                final InputStreamReader inReader = new InputStreamReader(in, StandardCharsets.UTF_8);
                final BufferedReader bufferedReader = new BufferedReader(inReader);

                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    result.append(line);
                }
                bufferedReader.close();
                inReader.close();
                in.close();

                return result.toString();
            }
            return "";
        }
    }
}

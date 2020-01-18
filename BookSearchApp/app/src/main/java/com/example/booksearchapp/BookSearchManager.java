package com.example.booksearchapp;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class BookSearchManager {

    private static final String BOOK_SEARCH_API_URL = "https://www.googleapis.com/books/v1/volumes?q=";
    private static final String BOOK_SEARCH_API_KEY_ITEMS = "items";
    private static final String BOOK_SEARCH_API_KEY_VOLUMES_INFO = "volumeInfo";
    private static final String BOOK_SEARCH_API_KEY_TITLE = "title";

    private OnBookSearchListener mListener;

    interface OnBookSearchListener {
        void finishSearch(List<String> results);
    }

    BookSearchManager(String keyword, OnBookSearchListener listener) {
        mListener = listener;
        new BookSearchTask().execute(keyword);
    }

    public class BookSearchTask extends AsyncTask<String, Void, List<String>> {

        @Override
        protected List<String> doInBackground(String... keyword) {
            try {
                return getSearchResult(keyword[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new ArrayList<>();
        }

        @Override
        protected void onPostExecute(List<String> results) {
            super.onPostExecute(results);
            if (mListener != null) mListener.finishSearch(results);
        }

        private List<String> getSearchResult(String keyword) throws IOException {
            final int CONNECTION_TIMEOUT = 30 * 1000;
            final int READ_TIMEOUT = 30 * 1000;

            List<String> resultList = new ArrayList<>();

            String searchUrl = BOOK_SEARCH_API_URL + keyword;
            HttpURLConnection conn = (HttpURLConnection) new URL(searchUrl).openConnection();
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

                String jsonText = result.toString();
                try {
                    JSONObject jsonObject = new JSONObject(jsonText);
                    JSONArray jsonArray = jsonObject.getJSONArray(BOOK_SEARCH_API_KEY_ITEMS);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject itemJsonObject = jsonArray.getJSONObject(i);
                        JSONObject volumeInfoJsonObject =
                                itemJsonObject.getJSONObject(BOOK_SEARCH_API_KEY_VOLUMES_INFO);
                        String title = volumeInfoJsonObject.getString(BOOK_SEARCH_API_KEY_TITLE);
                        resultList.add(title);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return resultList;
        }
    }
}

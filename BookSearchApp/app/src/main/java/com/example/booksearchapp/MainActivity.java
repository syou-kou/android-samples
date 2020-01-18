package com.example.booksearchapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements BookSearchManager.OnBookSearchListener {

    private EditText mSearchText;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSearchText = findViewById(R.id.search_text);
        mSearchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_SEARCH) {
                    String keyword = textView.getText().toString().trim();
                    new BookSearchManager(keyword, MainActivity.this);
                    InputMethodManager inputMethodManager =
                            (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (inputMethodManager != null) {
                        inputMethodManager.hideSoftInputFromWindow(
                                textView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                }
                return false;
            }
        });

        ListView listView = findViewById(R.id.list_view);
        TextView emptyText = findViewById(R.id.empty_text);
        listView.setEmptyView(emptyText);

        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        boolean result = true;

        if (id == R.id.action_delete_search) {
            mSearchText.setText("");
        } else {
            result = super.onOptionsItemSelected(item);
        }
        return result;
    }

    @Override
    public void finishSearch(List<String> results) {
        mAdapter.clear();
        mAdapter.addAll(results);
        mAdapter.notifyDataSetChanged();
    }
}

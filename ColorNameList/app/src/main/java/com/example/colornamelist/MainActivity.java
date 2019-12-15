package com.example.colornamelist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_COLOR_RES_ID = "key_color_res_id";
    private int[] colorResIds = {
            R.color.colorPaleGreen,
            R.color.colorPaleTurquoise,
            R.color.colorPaleVioletRed,
            R.color.colorPapayaWhip,
            R.color.colorPeachPuff,
            R.color.colorPeru,
            R.color.colorPink
    };
    private int[] colorNames = {
            R.string.pale_green,
            R.string.pale_turquoise,
            R.string.pale_violet_red,
            R.string.papaya_whip,
            R.string.peach_puff,
            R.string.peru,
            R.string.pink
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ListViewを読み込む
        ListView listView = findViewById(R.id.list_view);
        // 表示するデータを用意する
        final ArrayList<String> dataList = new ArrayList<>();
        for (int name : colorNames) {
            dataList.add(getString(name));
        }
        // アダプターを作成する
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        // ListViewにアダプターをセットする
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                int resId = colorResIds[position];
                Intent intent = new Intent(MainActivity.this, ColorActivity.class);
                intent.putExtra(KEY_COLOR_RES_ID, resId);
                startActivity(intent);
            }
        });
    }
}

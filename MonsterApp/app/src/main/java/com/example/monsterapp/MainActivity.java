package com.example.monsterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView mMonsterListText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMonsterListText = findViewById(R.id.monster_list_text);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //TODO 1.表示に使うデータを入れるリスト
        ArrayList<MonsterDataItem> itemList = new ArrayList<>();

        //TODO 2.読み込み専用のデータベースインスタンスを取得する処理
        SQLiteDatabase db = new MonsterDataOpenHelper(this).getReadableDatabase();

        //TODO 3.データベースから欲しいデータのカーソルを取り出す処理
        Cursor cursor = db.query(
                MonsterDataOpenHelper.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        //TODO 4.カーソルを使ってデータを取り出す処理
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(MonsterDataOpenHelper._ID));
            String no = cursor.getString(cursor.getColumnIndex(MonsterDataOpenHelper.COLUMN_NO));
            String name = cursor.getString(cursor.getColumnIndex(MonsterDataOpenHelper.COLUMN_NAME));
            itemList.add(new MonsterDataItem(id, no, name));
        }

        //TODO 5.カーソルを閉じ、その後データベースも閉じる
        cursor.close();
        db.close();

        //TODO 6.取り出したデータを表示用に一つの文に加工
        StringBuilder sb = new StringBuilder();
        for (MonsterDataItem item : itemList) {
            sb.append(item.toString()).append("\n");
        }

        //TODO 7.表示
        mMonsterListText.setText(sb);
    }
}

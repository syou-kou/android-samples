package com.example.settingpreference;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity
        implements SettingFragment.OnSelectMenuCallbackListener, SettingEditFragment.OnSettingEditListener {

    private static final String PREFERENCE_NAME = "setting_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, SettingFragment.newInstance())
                .commit();
    }

    @Override
    public void onSelectMenu(SettingFragment.SelectMenu menu) {
        // ボタンを押されたときの処理
        Fragment fragment = null;
        switch (menu) {
            case NAME:
                fragment = SettingEditFragment.newInstance(SettingEditFragment.EditType.USER_NAME);
                break;
            case PASSWORD:
                fragment = SettingEditFragment.newInstance(SettingEditFragment.EditType.PASSWORD);
                break;
            case AGE:
                fragment = SettingEditFragment.newInstance(SettingEditFragment.EditType.AGE);
                break;
        }
        if (fragment == null) {
            return;
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right
        );
        transaction.replace(R.id.main_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public String onLoadSaveData(String key) {
        // Preferenceでデータを読み込む処理
        SharedPreferences preferences = getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return preferences.getString(key, "");
    }

    @Override
    public void onSaveBtnTap(String key, String value) {
        // Preferenceでデータを保存する処理
        SharedPreferences preferences = getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        preferences.edit().putString(key, value).apply();
    }

    @Override
    public void onBackPage() {
        onBackPressed();
    }
}

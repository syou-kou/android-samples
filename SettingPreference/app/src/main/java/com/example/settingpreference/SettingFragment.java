package com.example.settingpreference;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SettingFragment extends Fragment implements View.OnClickListener {

    enum SelectMenu {
        NAME, PASSWORD, AGE
    }

    private OnSelectMenuCallbackListener mListener;

    private SettingFragment() {}

    static SettingFragment newInstance() {
        return new SettingFragment();
    }

    public interface OnSelectMenuCallbackListener {
        void onSelectMenu(SelectMenu menu);
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.name_setting).setOnClickListener(this);
        view.findViewById(R.id.password_setting).setOnClickListener(this);
        view.findViewById(R.id.age_setting).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        int selectedId = view.getId();
        switch (selectedId) {
            case R.id.name_setting:
                // ユーザー名タップ時の処理
                if (mListener != null) {
                    mListener.onSelectMenu(SelectMenu.NAME);
                }
                break;
            case R.id.password_setting:
                // パスワードタップ時の処理
                if (mListener != null) {
                    mListener.onSelectMenu(SelectMenu.PASSWORD);
                }
                break;
            case R.id.age_setting:
                // 年齢タップ時の処理
                if (mListener != null) {
                    mListener.onSelectMenu(SelectMenu.AGE);
                }
                break;
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            mListener = (OnSelectMenuCallbackListener)context;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }
}

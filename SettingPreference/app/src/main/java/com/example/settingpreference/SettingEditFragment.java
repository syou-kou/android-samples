package com.example.settingpreference;

import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SettingEditFragment extends Fragment {

    private SettingEditFragment() {}
    private EditType mSelectType = null;
    private OnSettingEditListener mListener;

    private static final String TYPE_TEXT = "type_text";
    private static final String TYPE_NUMBER = "type_number";
    private static final String KEY_TYPE = "key_type";

    enum EditType {
        USER_NAME(TYPE_TEXT, "ユーザー名", "ユーザー名を入力してください。"),
        PASSWORD(TYPE_TEXT, "パスワード", "パスワードを入力してください。"),
        AGE(TYPE_NUMBER, "年齢", "年齢を入力してください。");

        String type;
        String title;
        String hintMsg;
        EditType(String type, String title, String hintMsg) {
            this.type = type;
            this.title = title;
            this.hintMsg = hintMsg;
        }
        String getType() {
            return this.type;
        }
        String getTitle() {
            return this.title;
        }
        String getHintMsg() {
            return this.hintMsg;
        }
    }

    static SettingEditFragment newInstance(EditType type) {
        SettingEditFragment fragment = new SettingEditFragment();
        Bundle args = new Bundle();
        args.putString(KEY_TYPE, type.name());
        fragment.setArguments(args);
        return fragment;
    }

    interface OnSettingEditListener {
        String onLoadSaveData(String key);
        void onSaveBtnTap(String key, String value);
        void onBackPage();
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        String typeName = null;
        if (getArguments() != null) {
            typeName = getArguments().getString(KEY_TYPE);
        }
        if (EditType.USER_NAME.name().equals(typeName)) {
            mSelectType = EditType.USER_NAME;
        } else if (EditType.PASSWORD.name().equals(typeName)) {
            mSelectType = EditType.PASSWORD;
        } else if (EditType.AGE.name().equals(typeName)) {
            mSelectType = EditType.AGE;
        }
        return inflater.inflate(R.layout.fragment_setting_edit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView titleText = view.findViewById(R.id.title_text);
        final EditText editText = view.findViewById(R.id.edit_text);

        if (mSelectType.getType().equals(TYPE_TEXT)) {
            editText.setInputType(InputType.TYPE_CLASS_TEXT);
        } else if (mSelectType.getType().equals(TYPE_NUMBER)) {
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        }

        titleText.setText(mSelectType.getTitle());
        editText.setHint(mSelectType.getHintMsg());

        String value = mListener.onLoadSaveData(mSelectType.name());
        editText.setText(value);

        view.findViewById(R.id.save_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputStr = editText.getText().toString();
                if (inputStr.isEmpty()) {
                    return;
                }
                mListener.onSaveBtnTap(mSelectType.name(), inputStr);
                mListener.onBackPage();
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            mListener = (OnSettingEditListener)context;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }
}

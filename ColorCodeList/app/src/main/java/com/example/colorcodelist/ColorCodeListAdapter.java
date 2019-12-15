package com.example.colorcodelist;

import android.app.LauncherActivity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ColorCodeListAdapter extends ArrayAdapter<ColorCodeItem> {

    private LayoutInflater mLayoutInflater;
    private List<ColorCodeItem> mDataList;

    public ColorCodeListAdapter(@NonNull Context context, @NonNull List<ColorCodeItem> dataList) {
        super(context, R.layout.layout_list_item, dataList);
        mLayoutInflater = LayoutInflater.from(context);
        mDataList = dataList;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Nullable
    @Override
    public ColorCodeItem getItem(int position) {
        return mDataList.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ListItemHolder holder;

        if (convertView == null) {
            // convertViewがnullの場合は、新規にViewを作成する
            convertView = mLayoutInflater.inflate(R.layout.layout_list_item, parent, false);
            ImageView imageView = convertView.findViewById(R.id.color_image);
            TextView textView = convertView.findViewById(R.id.color_code_text);

            holder = new ListItemHolder();
            holder.setColorImageView(imageView);
            holder.setColorTextView(textView);
            convertView.setTag(holder);
        } else {
            // convertViewがnullでない場合は、Viewを再利用する
            holder = (ListItemHolder)convertView.getTag();
        }
        ColorCodeItem item = mDataList.get(position);
        // 色を設定
        holder.getColorImageView().setBackgroundResource(item.getColorResId());
        // タイトルを設定
        holder.getColorTextView().setText(item.getColorNameId());
        return convertView;
    }

    private class ListItemHolder {
        private ImageView colorImageView;
        private TextView colorTextView;

        ImageView getColorImageView() {
            return colorImageView;
        }

        void setColorImageView(ImageView colorImageView) {
            this.colorImageView = colorImageView;
        }

        TextView getColorTextView() {
            return colorTextView;
        }

        void setColorTextView(TextView colorTextView) {
            this.colorTextView = colorTextView;
        }
    }
}

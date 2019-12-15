package com.example.colorcodelist;

public class ColorCodeItem {

    private int colorResId;
    private int colorNameId;

    ColorCodeItem(int colorResId, int colorNameId) {
        this.colorResId = colorResId;
        this.colorNameId = colorNameId;
    }

    public int getColorResId() {
        return colorResId;
    }

    public void setColorResId(int colorResId) {
        this.colorResId = colorResId;
    }

    public int getColorNameId() {
        return colorNameId;
    }

    public void setColorNameId(int colorNameId) {
        this.colorNameId = colorNameId;
    }
}
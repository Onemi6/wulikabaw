package com.example.wulikabaw;

import android.app.Application;

public class MyApplication extends Application {
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
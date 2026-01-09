package com.gnssmonitor.ui.config;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ConfigViewModel extends ViewModel {

    private final MutableLiveData<String> mText = new MutableLiveData<>("");

    public void setText(String value) {
        mText.setValue(value);
    }

    public LiveData<String> getText() {
        return mText;
    }
}
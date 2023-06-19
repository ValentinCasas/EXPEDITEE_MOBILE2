package com.example.expeditee_mobile.ui.cobro;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CobroViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public CobroViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
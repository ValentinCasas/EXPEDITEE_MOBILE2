package com.example.expeditee_mobile.ui.pendientes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PendientesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public PendientesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
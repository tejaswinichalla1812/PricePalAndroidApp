package com.app.pricepal.ui.items;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ItemsViewModel extends ViewModel {
    private final MutableLiveData<String> mText;
    public ItemsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is items fragment");
    }
    public LiveData<String> getText() {
        return mText;
    }
}
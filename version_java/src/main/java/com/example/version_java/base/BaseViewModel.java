package com.example.version_java.base;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

public abstract class BaseViewModel extends AndroidViewModel {


    Handler handler = new Handler(Looper.getMainLooper());

    private final MutableLiveData _viewStateLiveData;
    @NotNull
    private final LiveData viewStateLiveData;

    @NotNull
    public final LiveData getViewStateLiveData() {
        return this.viewStateLiveData;
    }

    protected final void viewStateChanged(@NotNull final ViewState viewState) {
        handler.post(() -> {
            BaseViewModel.this._viewStateLiveData.setValue(viewState);
            BaseViewModel.this._viewStateLiveData.setValue((Object) null);
        });
    }

    public BaseViewModel(@NotNull Application application) {
        super(application);
        this._viewStateLiveData = new MutableLiveData();
        this.viewStateLiveData = (LiveData) this._viewStateLiveData;
    }
}


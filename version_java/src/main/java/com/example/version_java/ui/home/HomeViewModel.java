package com.example.version_java.ui.home;

import android.app.Application;

import com.example.version_java.base.BaseViewModel;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends BaseViewModel {

    public void permissionGrant() {
        viewStateChanged(HomeViewState.PermissionGrant.INSTANCE);
    }

    @Inject
    HomeViewModel(@NotNull Application application) {
        super(application);
    }

    ;
}

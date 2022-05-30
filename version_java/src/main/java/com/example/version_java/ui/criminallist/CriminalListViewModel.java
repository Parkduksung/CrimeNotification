package com.example.version_java.ui.criminallist;

import android.app.Application;

import com.example.version_java.base.BaseViewModel;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CriminalListViewModel extends BaseViewModel {


    @Inject
    CriminalListViewModel(
            @NotNull Application application
    ) {
        super(application);
    }


}

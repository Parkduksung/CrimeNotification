package com.example.crimenotification.ui.splash

import android.app.Application
import com.example.crimenotification.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(app: Application) : BaseViewModel(app) {


    fun routeHome() {
        viewStateChanged(SplashViewState.RouteHome)
    }

}
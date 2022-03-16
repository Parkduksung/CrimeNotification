package com.example.crimenotification.ui.splash

import com.example.crimenotification.base.ViewState

sealed class SplashViewState : ViewState {
    object RouteHome : SplashViewState()
    data class Error(val message : String) : SplashViewState()
}
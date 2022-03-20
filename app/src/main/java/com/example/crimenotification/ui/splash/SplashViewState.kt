package com.example.crimenotification.ui.splash

import com.example.crimenotification.base.ViewState

/**
 * SplashActivity  에 화면상태 변화를 나타내는 클래스
 */
sealed class SplashViewState : ViewState {
    object RouteHome : SplashViewState()
    data class Error(val message : String) : SplashViewState()
}
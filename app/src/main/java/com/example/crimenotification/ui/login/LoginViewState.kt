package com.example.crimenotification.ui.login

import com.example.crimenotification.base.ViewState

sealed class LoginViewState : ViewState {
    object RouteRegister : LoginViewState()
    object RouteHome : LoginViewState()
    data class Error(val message: String) : LoginViewState()
    data class EnableInput(val isEnable: Boolean) : LoginViewState()
    object ShowProgress : LoginViewState()
    object HideProgress : LoginViewState()
}

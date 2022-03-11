package com.example.crimenotification.ui.home

import com.example.crimenotification.base.ViewState

sealed class HomeViewState : ViewState {
    data class Error(val errorMessage: String) : HomeViewState()
    object PermissionGrant : HomeViewState()
}
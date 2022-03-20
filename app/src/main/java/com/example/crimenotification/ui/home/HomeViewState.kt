package com.example.crimenotification.ui.home

import com.example.crimenotification.base.ViewState

/**
 * HomeActivity  에 화면상태 변화를 나타내는 클래스
 */
sealed class HomeViewState : ViewState {
    data class Error(val errorMessage: String) : HomeViewState()
    object PermissionGrant : HomeViewState()
}
package com.example.crimenotification.ui.home

import android.app.Application
import com.example.crimenotification.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(app: Application) : BaseViewModel(app){

    /**
     * 퍼미션 허용했을때 상태값이 PermissionGrant 변한다.
     */
    fun permissionGrant() {
        viewStateChanged(HomeViewState.PermissionGrant)
    }

}
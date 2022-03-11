package com.example.crimenotification.ui.home

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.viewModels
import com.example.crimenotification.BuildConfig
import com.example.crimenotification.R
import com.example.crimenotification.base.BaseActivity
import com.example.crimenotification.base.ViewState
import com.example.crimenotification.databinding.ActivityHomeBinding
import com.example.crimenotification.ext.showToast
import com.example.crimenotification.ui.map.MapFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {

    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
    }

    private fun initViewModel() {
        homeViewModel.viewStateLiveData.observe(this) { viewState: ViewState? ->
            (viewState as? HomeViewState)?.let { onChangedHomeViewState(viewState) }
        }
    }

    private fun onChangedHomeViewState(viewState: HomeViewState) {
        when (viewState) {
            is HomeViewState.Error -> {
                showToast(message = viewState.errorMessage)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == MapFragment.REQUEST_FINE_LOCATION_PERMISSIONS_REQUEST_CODE) {

            when {
                grantResults.isEmpty() -> {
                    showToast(message = "권한이 없습니다.")
                }

                grantResults[0] == PackageManager.PERMISSION_GRANTED -> {
                    showToast(message = "권한이 허용되었습니다.")
                    homeViewModel.permissionGrant()
                }

                else -> {
                    val intent = Intent()
                    intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                    val uri = Uri.fromParts(
                        "package",
                        BuildConfig.APPLICATION_ID,
                        null
                    )
                    intent.data = uri
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                }
            }
        }
    }

}
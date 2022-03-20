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

    /**
     * 뒤로가기 누르고 기다리는 시간 변수.
     */
    private var backWait: Long = INIT_TIME

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
    }

    /**
     * 뷰모델 초기화
     */
    private fun initViewModel() {
        homeViewModel.viewStateLiveData.observe(this) { viewState: ViewState? ->
            (viewState as? HomeViewState)?.let { onChangedHomeViewState(viewState) }
        }
    }

    /**
     * 상태에 따른 화면변화를 나타냄
     */
    private fun onChangedHomeViewState(viewState: HomeViewState) {
        when (viewState) {
            is HomeViewState.Error -> {
                showToast(message = viewState.errorMessage)
            }
        }
    }

    /**
     * GPS 권한 결과에 대한 처리.
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == MapFragment.REQUEST_FINE_LOCATION_PERMISSIONS_REQUEST_CODE) {

            when {

                /**
                 * GPS 권한 x
                 */
                grantResults.isEmpty() -> {
                    showToast(message = "권한이 없습니다.")
                }

                /**
                 * GPS 권한 o
                 */
                grantResults[0] == PackageManager.PERMISSION_GRANTED -> {
                    showToast(message = "권한이 허용되었습니다.")
                    homeViewModel.permissionGrant()
                }

                /**
                 * GPS 권한 시스템 실행.
                 */
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

    /**
     * 뒤로가기 버튼 클릭시 사용자에게 화면이 꺼진다는 메세지를 보여주는 기능
     * 뒤로가기 누른 후 , 2초안에 뒤로가기를 한번더 누를시에 종료 o
     * 뒤로가기 누른 후 , 2초안에 뒤로가기를 한번더 누르지 않을시 종료 x
     */
    override fun onBackPressed() {
        if (System.currentTimeMillis() - backWait >= LIMIT_TIME) {
            backWait = System.currentTimeMillis()
            showToast(message = "뒤로가기 버튼을 한번 더 누르면 종료됩니다.")
        } else {
            super.onBackPressed()
        }
    }

    companion object {

        /**
         * 뒤로가기 구현 관련된 변수들.
         */
        private const val INIT_TIME = 0L
        private const val LIMIT_TIME = 2000

    }
}
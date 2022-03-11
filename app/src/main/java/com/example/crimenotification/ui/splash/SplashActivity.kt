package com.example.crimenotification.ui.splash

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.example.crimenotification.R
import com.example.crimenotification.base.BaseActivity
import com.example.crimenotification.databinding.ActivitySplashBinding
import com.example.crimenotification.ui.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    private val splashViewModel by viewModels<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        initUi()
    }

    private fun initUi() {
        binding.lottieView.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}

            override fun onAnimationEnd(animation: Animator) {
                splashViewModel.routeHome()
            }

            override fun onAnimationCancel(animation: Animator) {}

            override fun onAnimationRepeat(animation: Animator) {}
        })
    }

    private fun initViewModel() {
        splashViewModel.viewStateLiveData.observe(this) { viewState ->
            (viewState as? SplashViewState)?.let {
                onChangedSplashViewState(
                    viewState
                )
            }
        }
    }


    private fun onChangedSplashViewState(viewState: SplashViewState) {
        when (viewState) {
            is SplashViewState.RouteHome -> {
                startActivity(Intent(this@SplashActivity, HomeActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                })
            }
        }
    }
}
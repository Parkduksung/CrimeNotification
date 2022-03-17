package com.example.crimenotification.ui.splash

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.example.crimenotification.R
import com.example.crimenotification.base.BaseActivity
import com.example.crimenotification.databinding.ActivitySplashBinding
import com.example.crimenotification.ext.showToast
import com.example.crimenotification.ui.home.HomeActivity
import com.example.crimenotification.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlin.system.exitProcess

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    private val splashViewModel by viewModels<SplashViewModel>()

    private var isRoute = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        initUi()
    }

    private fun initUi() {
        binding.lottieView.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}

            override fun onAnimationEnd(animation: Animator) {
                if (isRoute) {
                    startActivity(Intent(this@SplashActivity, LoginActivity::class.java).apply {
                        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    })
                } else {
                    with(binding) {
                        tvLoading.isVisible = true
                        tvLoading.startAnimation(
                            AnimationUtils.loadAnimation(
                                applicationContext,
                                R.anim.anim_brick
                            )
                        )
                        lottieView.playAnimation()
                    }
                }
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
                isRoute = true
            }

            is SplashViewState.Error -> {
                showToast(message = viewState.message)
                exitProcess(0)
            }
        }
    }
}
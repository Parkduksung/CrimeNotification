package com.example.crimenotification.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.example.crimenotification.R
import com.example.crimenotification.base.BaseActivity
import com.example.crimenotification.databinding.ActivityLoginBinding
import com.example.crimenotification.ext.showToast
import com.example.crimenotification.ui.home.HomeActivity
import com.example.crimenotification.ui.register.RegisterActivity
import com.example.crimenotification.ui.register.RegisterViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private val loginViewModel by viewModels<LoginViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initUi()
        initViewModel()
    }

    private fun initUi() {
        binding.inputPassLogin.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                loginViewModel.login()
                true
            } else {
                false
            }
        }
    }

    private fun initViewModel() {
        binding.viewModel = loginViewModel
        loginViewModel.viewStateLiveData.observe(this) { viewState ->
            (viewState as? LoginViewState)?.let {
                onChangedLoginViewState(
                    viewState
                )
            }
        }
    }

    private fun onChangedLoginViewState(viewState: LoginViewState) {
        when (viewState) {

            is LoginViewState.Error -> {
                showToast(message = viewState.message)
            }

            is LoginViewState.EnableInput -> {
                with(binding) {
                    inputEmailLogin.isEnabled = viewState.isEnable
                    inputPassLogin.isEnabled = viewState.isEnable
                }
            }

            is LoginViewState.RouteHome -> {
                startActivity(Intent(this@LoginActivity, HomeActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                })
            }

            is LoginViewState.RouteRegister -> {
                with(binding) {
                    inputEmailLogin.text.clear()
                    inputPassLogin.text.clear()
                }
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            }

            is LoginViewState.ShowProgress -> {
                binding.progressbar.bringToFront()
                binding.progressbar.isVisible = true
            }

            is LoginViewState.HideProgress -> {
                binding.progressbar.isVisible = false
            }
        }
    }
}
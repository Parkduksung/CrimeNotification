package com.example.crimenotification.ui.register

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.example.crimenotification.R
import com.example.crimenotification.base.BaseActivity
import com.example.crimenotification.databinding.ActivityRegisterBinding
import com.example.crimenotification.ext.showToast
import com.example.crimenotification.ui.home.HomeActivity
import com.example.crimenotification.ui.map.MapViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : BaseActivity<ActivityRegisterBinding>(R.layout.activity_register) {

    private val registerViewModel by viewModels<RegisterViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initUi()
        initViewModel()
    }

    private fun initUi() {
        binding.inputPassRegisterOk.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                registerViewModel.register()
                true
            } else {
                false
            }
        }
    }

    private fun initViewModel() {
        binding.viewModel = registerViewModel
        registerViewModel.viewStateLiveData.observe(this) { viewState ->
            (viewState as? RegisterViewState)?.let {
                onChangedRegisterViewState(
                    viewState
                )
            }
        }
    }

    private fun onChangedRegisterViewState(viewState: RegisterViewState) {
        when (viewState) {

            is RegisterViewState.Error -> {
                showToast(message = viewState.message)
            }

            is RegisterViewState.EnableInput -> {
                with(binding) {
                    inputEmailRegister.isEnabled = viewState.isEnable
                    inputPassRegisterOk.isEnabled = viewState.isEnable
                    inputPassRegister.isEnabled = viewState.isEnable
                }
            }

            is RegisterViewState.RouteHome -> {
                startActivity(Intent(this@RegisterActivity, HomeActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                })
            }

            is RegisterViewState.ShowProgress -> {
                binding.progressbar.bringToFront()
                binding.progressbar.isVisible = true
            }

            is RegisterViewState.HideProgress -> {
                binding.progressbar.isVisible = false
            }
        }
    }
}
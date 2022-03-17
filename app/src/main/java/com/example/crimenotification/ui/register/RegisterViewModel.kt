package com.example.crimenotification.ui.register

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.crimenotification.base.BaseViewModel
import com.example.crimenotification.data.repo.FirebaseRepository
import com.example.crimenotification.ext.ioScope
import com.example.crimenotification.ext.loginUser
import com.example.crimenotification.ui.login.LoginViewState
import com.example.crimenotification.ui.map.MapViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    app: Application,
    private val firebaseRepository: FirebaseRepository
) : BaseViewModel(app) {


    val inputEmailLiveData = MutableLiveData<String>()
    val inputPasswordLiveData = MutableLiveData<String>()
    val inputPasswordOkLiveData = MutableLiveData<String>()


    fun register() {
        ioScope {
            viewStateChanged(MapViewState.ShowProgress)
            viewStateChanged(LoginViewState.EnableInput(false))
            val checkEmail = async { checkEmail() }
            val checkPassword = async { checkPassword() }
            val checkPasswordOk = async { checkPasswordOk() }

            checkUser(
                checkEmail.await(),
                checkPassword.await(),
                checkPasswordOk.await()
            )?.let { person ->
                firebaseRepository.register(
                    person.email,
                    person.password
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        viewStateChanged(RegisterViewState.RouteHome)
                        viewStateChanged(MapViewState.HideProgress)
                    } else {
                        viewStateChanged(RegisterViewState.Error("회원가입을 실패하였습니다."))
                        viewStateChanged(MapViewState.HideProgress)
                    }
                }
            } ?: viewStateChanged(MapViewState.HideProgress)

            viewStateChanged(RegisterViewState.EnableInput(true))
        }
    }


    private fun checkUser(
        checkEmail: Boolean,
        checkPassword: Boolean,
        checkPasswordOk: Boolean,
    ): Person? {
        return if (checkEmail && checkPassword && checkPasswordOk) {
            Person(inputEmailLiveData.value!!, inputPasswordLiveData.value!!)
        } else {
            null
        }
    }

    private fun checkEmail(): Boolean {
        return when {
            inputEmailLiveData.value.isNullOrEmpty() -> {
                viewStateChanged(RegisterViewState.Error("이메일을 입력해 주세요."))
                false
            }
            else -> true
        }
    }

    private fun checkPassword(): Boolean {
        return when {
            inputPasswordLiveData.value.isNullOrEmpty() -> {
                viewStateChanged(RegisterViewState.Error("비밀번호를 입력해 주세요."))
                false
            }
            else -> true
        }
    }

    private fun checkPasswordOk(): Boolean {
        return when {
            inputPasswordLiveData.value != inputPasswordOkLiveData.value -> {
                viewStateChanged(RegisterViewState.Error("비밀번호 재입력을 올바르게 입력해 주세요."))
                false
            }
            else -> true
        }
    }

    data class Person(
        val email: String,
        val password: String
    )

}
package com.example.version_java.ui.register;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.version_java.base.BaseViewModel;
import com.example.version_java.data.repo.FirebaseRepository;
import com.example.version_java.ui.login.Person;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class RegisterViewModel extends BaseViewModel {

    private final FirebaseRepository firebaseRepository;

    /**
     * id, password 입력하는 값을 가져오는 변수들
     */
    public MutableLiveData<String> inputEmailLiveData = new MutableLiveData<>();
    public MutableLiveData<String> inputPasswordLiveData = new MutableLiveData<>();
    public MutableLiveData<String> inputPasswordOkLiveData = new MutableLiveData<>();


    /**
     * 로그인 로직
     * 흐름 : 프로그래스바 보여줌 -> 입력을 false -> id,password 공란체크 -> 파이어베이스 로그인 결과 체크
     */
    public void register() {

        new Thread(() -> {
            viewStateChanged(RegisterViewState.ShowProgress.INSTANCE);
            viewStateChanged(new RegisterViewState.EnableInput(false));

            boolean checkEmail = checkEmail();
            boolean checkPassword = checkPassword();
            boolean checkPasswordOk = checkPasswordOk();

            Person person = checkUser(checkEmail, checkPassword, checkPasswordOk);

            if (person != null) {

                /**
                 * 파이어베이스 회원가입.
                 */
                firebaseRepository.register(person.getEmail(), person.getPassword(), isRegister -> {

                    if ((Boolean) isRegister) {
                        /**
                         * 회원가입 성공시, 홈화면 전환, 프로그래스바 숨기기
                         */
                        viewStateChanged(RegisterViewState.RouteHome.INSTANCE);
                        viewStateChanged(RegisterViewState.HideProgress.INSTANCE);
                    } else {
                        /**
                         * 회원가입 실패시, 실패메시지 보여주기, 프로그래스바 숨기기
                         */
                        viewStateChanged(new RegisterViewState.Error("회원가입을 실패하였습니다."));
                        viewStateChanged(RegisterViewState.HideProgress.INSTANCE);
                    }
                    return null;
                });
            } else {
                viewStateChanged(RegisterViewState.HideProgress.INSTANCE);
            }

            viewStateChanged(new RegisterViewState.EnableInput(true));
        }).start();

    }

    /**
     * 입력한 id, password , passwordOk 체크 여부에 따른 결과 체크
     * 성공시, id, password 를 구성하는 Person 을 반환
     * 실패시, null 을 반환.
     */
    private Person checkUser(
            Boolean checkEmail,
            Boolean checkPassword,
            Boolean checkPasswordOk
    ) {
        if (checkEmail && checkPassword && checkPasswordOk) {
            return new Person(inputEmailLiveData.getValue(), inputPasswordLiveData.getValue());
        } else {
            return null;
        }
    }

    /**
     * 입력한 id 체크
     * 성공시 true, 실패시 false 반환.
     */
    private Boolean checkEmail() {
        synchronized (this) {
            if (inputEmailLiveData.getValue() == null) {
                viewStateChanged(new RegisterViewState.Error("이메일을 입력해 주세요."));
                return false;
            } else {
                if (inputEmailLiveData.getValue().isEmpty()) {
                    viewStateChanged(new RegisterViewState.Error("이메일을 입력해 주세요."));
                    return false;
                } else {
                    return true;
                }
            }
        }
    }

    /**
     * 입력한 password 체크
     * 성공시 true, 실패시 false 반환.
     */
    private Boolean checkPassword() {
        synchronized (this) {
            if (inputPasswordLiveData.getValue() == null) {
                viewStateChanged(new RegisterViewState.Error("비밀번호를 입력해 주세요."));
                return false;
            } else {
                if (inputPasswordLiveData.getValue().isEmpty()) {
                    viewStateChanged(new RegisterViewState.Error("비밀번호를 입력해 주세요."));
                    return false;
                } else {
                    return true;
                }
            }
        }
    }

    /**
     * 입력한 passwordOk 체크
     * 성공시 true, 실패시 false 반환.
     */
    private Boolean checkPasswordOk() {
        synchronized (this) {
            if (!inputPasswordLiveData.getValue().equals(inputPasswordOkLiveData.getValue())) {
                viewStateChanged(new RegisterViewState.Error("비밀번호 재입력을 올바르게 입력해 주세요."));
                return false;
            } else {
                return true;
            }
        }
    }


    @Inject
    public RegisterViewModel(
            @NotNull Application application,
            FirebaseRepository firebaseRepository
    ) {
        super(application);
        this.firebaseRepository = firebaseRepository;
    }
}

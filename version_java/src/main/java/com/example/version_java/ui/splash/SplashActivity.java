package com.example.version_java.ui.splash;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.version_java.R;
import com.example.version_java.base.BaseActivity;
import com.example.version_java.databinding.ActivitySplashBinding;
import com.example.version_java.ui.login.LoginActivity;

import org.jetbrains.annotations.NotNull;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public final class SplashActivity extends BaseActivity {

    private boolean isRoute;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUi();
    }


    private void initUi() {
        ((ActivitySplashBinding) this.getBinding()).lottieView.addAnimatorListener(new Animator.AnimatorListener() {
            public void onAnimationStart(@NotNull Animator animation) {
            }

            public void onAnimationEnd(@NotNull Animator animation) {
                if (SplashActivity.this.isRoute) {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else {
                    ((ActivitySplashBinding) getBinding()).tvLoading.setVisibility(View.VISIBLE);
                    ((ActivitySplashBinding) getBinding()).tvLoading.startAnimation(AnimationUtils.loadAnimation(SplashActivity.this.getApplicationContext(), R.anim.anim_brick));
                    ((ActivitySplashBinding) getBinding()).lottieView.playAnimation();
                }
            }

            public void onAnimationCancel(@NotNull Animator animation) {
            }

            public void onAnimationRepeat(@NotNull Animator animation) {
            }
        });
    }

    public SplashActivity() {
        super(R.layout.activity_splash);
        this.setBinding((ActivitySplashBinding) this.getBinding());
    }

}
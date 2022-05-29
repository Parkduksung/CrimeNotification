package com.example.version_java.base;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class BaseActivity extends AppCompatActivity {

    protected ViewDataBinding binding;
    private final int layoutId;

    public BaseActivity(@LayoutRes int layoutId) {
        this.layoutId = layoutId;
    }

    @NotNull
    protected final ViewDataBinding getBinding() {
        return this.binding;
    }

    protected final void setBinding(@NotNull ViewDataBinding binding) {
        this.binding = binding;
    }


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ViewDataBinding binding = DataBindingUtil.setContentView((Activity) this, this.layoutId);
        this.binding = binding;
        this.setContentView(this.binding.getRoot());
    }

}

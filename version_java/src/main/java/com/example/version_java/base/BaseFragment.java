package com.example.version_java.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

public abstract class BaseFragment extends Fragment {
    protected ViewDataBinding binding;
    private final int layoutId;

    public BaseFragment(@LayoutRes int layoutId) {
        this.layoutId = layoutId;
    }

    @NotNull
    protected final ViewDataBinding getBinding() {
        return this.binding;
    }

    protected final void setBinding(@NotNull ViewDataBinding binding) {
        this.binding = binding;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, layoutId, container, false);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }
}

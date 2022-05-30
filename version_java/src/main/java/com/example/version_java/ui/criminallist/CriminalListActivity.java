package com.example.version_java.ui.criminallist;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.version_java.R;
import com.example.version_java.base.BaseActivity;
import com.example.version_java.databinding.ActivityCriminalListBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CriminalListActivity extends BaseActivity {

    private CriminalListViewModel criminalListViewModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModel();
    }

    private void initViewModel() {
        criminalListViewModel = new ViewModelProvider(this).get(CriminalListViewModel.class);
        ((ActivityCriminalListBinding) getBinding()).setViewModel(criminalListViewModel);
    }

    public CriminalListActivity() {
        super(R.layout.activity_criminal_list);
        this.setBinding((ActivityCriminalListBinding) this.getBinding());
    }
}

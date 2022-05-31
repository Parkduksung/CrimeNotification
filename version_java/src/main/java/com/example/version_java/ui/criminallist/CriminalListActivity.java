package com.example.version_java.ui.criminallist;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.version_java.R;
import com.example.version_java.base.BaseActivity;
import com.example.version_java.databinding.ActivityCriminalListBinding;
import com.example.version_java.ui.adapter.CriminalAdapter;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CriminalListActivity extends BaseActivity {

    private CriminalListViewModel criminalListViewModel;

    private CriminalAdapter criminalAdapter = new CriminalAdapter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUi();
        initViewModel();
    }

    private void initUi() {
        ((ActivityCriminalListBinding) getBinding()).rvCriminal.setAdapter(criminalAdapter);
        ((ActivityCriminalListBinding) getBinding()).content.setText(
                "반경 " + getIntent().getIntExtra("key_range", 500) + "M 에 있는 범죄자 리스트"
        );
    }

    /**
     * 뷰모델 초기화
     */
    private void initViewModel() {
        criminalListViewModel = new ViewModelProvider(this).get(CriminalListViewModel.class);
        ((ActivityCriminalListBinding) getBinding()).setViewModel(criminalListViewModel);
        criminalListViewModel.rangeObservableField.set(getIntent().getIntExtra("key_range", 500));
        criminalListViewModel.getCriminalList();
        criminalListViewModel.getViewStateLiveData().observe(this, viewState -> {
            if (viewState instanceof CriminalListViewState) {
                onChangedViewState((CriminalListViewState) viewState);
            }
        });
    }

    /**
     * 상태에 따른 화면변화를 나타냄
     */
    private void onChangedViewState(CriminalListViewState viewState) {
        if (viewState instanceof CriminalListViewState.Error) {
            Toast.makeText(this, ((CriminalListViewState.Error) viewState).getErrorMessage(), Toast.LENGTH_SHORT).show();
        } else if (viewState instanceof CriminalListViewState.ShowProgress) {
            ((ActivityCriminalListBinding) getBinding()).progressbar.bringToFront();
            ((ActivityCriminalListBinding) getBinding()).progressbar.setVisibility(View.VISIBLE);
            criminalAdapter.clear();
        } else if (viewState instanceof CriminalListViewState.HideProgress) {
            ((ActivityCriminalListBinding) getBinding()).progressbar.setVisibility(View.GONE);
        } else if (viewState instanceof CriminalListViewState.RenewCriminalList) {
            ((ActivityCriminalListBinding) getBinding()).criminalNum.setVisibility(View.VISIBLE);
            ((ActivityCriminalListBinding) getBinding()).criminalNum.setText("총 "+ ((CriminalListViewState.RenewCriminalList) viewState).getList().size() +" 명");
            criminalAdapter.renewAll(((CriminalListViewState.RenewCriminalList) viewState).getList());
        } else if (viewState instanceof CriminalListViewState.EmptyCriminalList) {
            ((ActivityCriminalListBinding) getBinding()).criminalNum.setVisibility(View.GONE);
            ((ActivityCriminalListBinding) getBinding()).criminalNum.setText("총 0명");
            criminalAdapter.clear();
        }
    }

    public CriminalListActivity() {
        super(R.layout.activity_criminal_list);
        this.setBinding((ActivityCriminalListBinding) this.getBinding());
    }
}

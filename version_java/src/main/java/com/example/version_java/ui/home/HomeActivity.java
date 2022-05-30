package com.example.version_java.ui.home;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.version_java.R;
import com.example.version_java.base.BaseActivity;
import com.example.version_java.databinding.ActivityHomeBinding;

public class HomeActivity extends BaseActivity {

    private HomeViewModel homeViewModel;

    /**
     * 뒤로가기 구현 관련된 변수들.
     */
    private static final Long INIT_TIME = 0L;
    private static final Long LIMIT_TIME = 2000L;

    private Long backWait = INIT_TIME;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModel();
    }

    private void initViewModel() {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        homeViewModel.getViewStateLiveData().observe(HomeActivity.this, viewState -> {
            if (viewState instanceof HomeViewState) {
                onChangedViewState((HomeViewState) viewState);
            }
        });
    }

    private void onChangedViewState(HomeViewState viewState) {
        if (viewState instanceof HomeViewState.Error) {
            Toast.makeText(this, ((HomeViewState.Error) viewState).getErrorMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * GPS 권한 결과에 대한 처리.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000) {

            if (grantResults.length == 0) {
                Toast.makeText(this, "권한이 없습니다.", Toast.LENGTH_SHORT).show();
            } else if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "권한이 허용되었습니다.", Toast.LENGTH_SHORT).show();
                homeViewModel.permissionGrant();
            }
        }
    }

    /**
     * 뒤로가기 버튼 클릭시 사용자에게 화면이 꺼진다는 메세지를 보여주는 기능
     * 뒤로가기 누른 후 , 2초안에 뒤로가기를 한번더 누를시에 종료 o
     * 뒤로가기 누른 후 , 2초안에 뒤로가기를 한번더 누르지 않을시 종료 x
     */
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - backWait >= LIMIT_TIME) {
            backWait = System.currentTimeMillis();
            Toast.makeText(this, "뒤로가기 버튼을 한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
        } else {
            super.onBackPressed();
        }
    }

    public HomeActivity() {
        super(R.layout.activity_home);
        this.setBinding((ActivityHomeBinding) this.getBinding());
    }
}

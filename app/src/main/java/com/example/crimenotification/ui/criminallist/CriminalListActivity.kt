package com.example.crimenotification.ui.criminallist

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.example.crimenotification.R
import com.example.crimenotification.base.BaseActivity
import com.example.crimenotification.base.ViewState
import com.example.crimenotification.databinding.ActivityCriminalListBinding
import com.example.crimenotification.ext.showToast
import com.example.crimenotification.ui.adapter.CriminalAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CriminalListActivity :
    BaseActivity<ActivityCriminalListBinding>(R.layout.activity_criminal_list) {

    private val criminalListViewModel by viewModels<CriminalListViewModel>()

    private val criminalAdapter by lazy { CriminalAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()
        initViewModel()
    }

    @SuppressLint("SetTextI18n")
    private fun initUi() {
        with(binding) {
            rvCriminal.adapter = criminalAdapter
            content.text = "반경 ${intent.getIntExtra(KEY_RANGE, 500)}M 에 있는 범죄자 리스트"
        }
    }

    private fun initViewModel() {
        criminalListViewModel.rangeObservableField.set(intent.getIntExtra(KEY_RANGE, 500))
        criminalListViewModel.getCriminalList()
        criminalListViewModel.viewStateLiveData.observe(this) { viewState: ViewState? ->
            (viewState as? CriminalListViewState)?.let { onChangedCriminalListViewState(viewState) }
        }
    }

    private fun onChangedCriminalListViewState(viewState: CriminalListViewState) {
        when (viewState) {
            is CriminalListViewState.Error -> {
                showToast(message = viewState.errorMessage)
            }

            is CriminalListViewState.ShowProgress -> {
                binding.progressbar.bringToFront()
                binding.progressbar.isVisible = true
                criminalAdapter.clear()
            }

            is CriminalListViewState.HideProgress -> {
                binding.progressbar.isVisible = false
            }

            is CriminalListViewState.RenewCriminalList -> {
                Log.d("결과", "여기 호출됨??")
                criminalAdapter.renewAll(viewState.list.sortedBy { it.distance })
            }
        }
    }

    companion object {
        const val KEY_RANGE = "key_range"
    }
}
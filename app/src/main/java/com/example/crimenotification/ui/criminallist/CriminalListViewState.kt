package com.example.crimenotification.ui.criminallist

import com.example.crimenotification.base.ViewState
import com.example.crimenotification.data.model.DistanceCriminal

/**
 * CriminalListActivity  에 화면상태 변화를 나타내는 클래스
 */
sealed class CriminalListViewState : ViewState {
    object ShowProgress : CriminalListViewState()
    object HideProgress : CriminalListViewState()
    object EmptyCriminalList : CriminalListViewState()
    data class RenewCriminalList(val list: List<DistanceCriminal>) : CriminalListViewState()
    data class Error(val errorMessage: String) : CriminalListViewState()
}
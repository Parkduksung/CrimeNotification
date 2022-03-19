package com.example.crimenotification.ui.criminallist

import com.example.crimenotification.base.ViewState
import com.example.crimenotification.data.model.DistanceCriminal

sealed class CriminalListViewState : ViewState {
    object ShowProgress : CriminalListViewState()
    object HideProgress : CriminalListViewState()
    data class RenewCriminalList(val list: List<DistanceCriminal>) : CriminalListViewState()
    data class Error(val errorMessage: String) : CriminalListViewState()
}
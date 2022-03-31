package uk.co.bubblebearapps.pantry.addstock.ui

import androidx.lifecycle.LiveData

interface AddStockViewModel {

    val viewState: LiveData<ViewState>

    fun onItemAdded(itemName: String)

    sealed class ViewState {
        object Idle : ViewState()
        object Loading : ViewState()
        object Error: ViewState()
    }
}

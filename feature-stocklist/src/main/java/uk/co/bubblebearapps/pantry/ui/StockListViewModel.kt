package uk.co.bubblebearapps.pantry.ui

import androidx.lifecycle.LiveData
import uk.co.bubblebearapps.pantry.domain.model.StockListItem

interface StockListViewModel {

    val viewState: LiveData<ViewState>

    fun onAddButtonPress()

    sealed class ViewState {
        object Loading : ViewState()
        data class Result(
            val list: List<StockListItem>,
        ) : ViewState()

        data class Error(val retryAction: () -> Unit) : ViewState()
    }
}
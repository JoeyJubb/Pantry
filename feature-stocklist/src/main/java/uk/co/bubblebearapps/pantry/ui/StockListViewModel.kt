package uk.co.bubblebearapps.pantry.ui

import androidx.lifecycle.LiveData
import uk.co.bubblebearapps.pantry.domain.model.StockListItem

interface StockListViewModel {

    val viewState: LiveData<ViewState>
    val events: EventLiveData<Event>

    fun onAddButtonPress()

    sealed class Event{
        object GoToAddStock : Event()
    }

    sealed class ViewState {
        object Empty : ViewState()

        data class Result(
            val list: List<StockListItem>,
        ) : ViewState()
    }
}
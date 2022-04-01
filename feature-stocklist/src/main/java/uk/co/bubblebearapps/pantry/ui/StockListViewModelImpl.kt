package uk.co.bubblebearapps.pantry.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uk.co.bubblebearapps.pantry.domain.GetStockList
import uk.co.bubblebearapps.pantry.domain.model.StockListItem
import uk.co.bubblebearapps.pantry.ui.StockListViewModel.Event
import uk.co.bubblebearapps.pantry.ui.StockListViewModel.ViewState
import javax.inject.Inject

@HiltViewModel
internal class StockListViewModelImpl @Inject constructor(
    private val getStockList: GetStockList,
) : ViewModel(), StockListViewModel {

    override val viewState = MutableLiveData<ViewState>(ViewState.Empty)
    override val events = EventLiveData<Event>()

    override fun onAddButtonPress() {
        events.value = Event.GoToAddStock
    }

    init {
        getStockList()
    }

    private fun getStockList() {
        viewModelScope.launch {
            getStockList(Unit)
                .collect(::onStockListUpdated)
        }
    }

    private fun onStockListUpdated(stockList: List<StockListItem>) {
        viewState.value = if (stockList.isNotEmpty()) {
            ViewState.Result(list = stockList)
        } else {
            ViewState.Empty
        }
    }
}

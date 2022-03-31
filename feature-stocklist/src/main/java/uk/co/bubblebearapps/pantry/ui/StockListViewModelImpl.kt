package uk.co.bubblebearapps.pantry.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uk.co.bubblebearapps.pantry.domain.Destination
import uk.co.bubblebearapps.pantry.domain.GetStockList
import uk.co.bubblebearapps.pantry.domain.Navigator
import uk.co.bubblebearapps.pantry.domain.model.StockListItem
import uk.co.bubblebearapps.pantry.ui.StockListViewModel.ViewState
import javax.inject.Inject

@HiltViewModel
internal class StockListViewModelImpl @Inject constructor(
    private val navigator: Navigator,
    private val getStockList: GetStockList,
) : ViewModel(), StockListViewModel {

    private val _viewState = MutableLiveData<ViewState>(ViewState.Empty)
    override val viewState: LiveData<ViewState> = _viewState

    override fun onAddButtonPress() {
        navigator.navigateTo(Destination.AddStock)
    }

    init {
        getStockList()
    }

    private fun getStockList() {
        viewModelScope.launch {
            getStockList(GetStockList.Params)
                .collect(::onStockListUpdated)
        }
    }

    private fun onStockListUpdated(stockList: List<StockListItem>) {
        _viewState.value = if (stockList.isNotEmpty()) {
            ViewState.Result(list = stockList)
        } else {
            ViewState.Empty
        }
    }
}

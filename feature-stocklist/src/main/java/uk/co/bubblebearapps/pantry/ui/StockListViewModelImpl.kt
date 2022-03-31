package uk.co.bubblebearapps.pantry.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import uk.co.bubblebearapps.pantry.domain.Destination
import uk.co.bubblebearapps.pantry.domain.GetStockList
import uk.co.bubblebearapps.pantry.domain.Navigator
import uk.co.bubblebearapps.pantry.ui.StockListViewModel.ViewState
import javax.inject.Inject

@HiltViewModel
internal class StockListViewModelImpl @Inject constructor(
    private val navigator: Navigator,
    private val getStockList: GetStockList,
) : ViewModel(), StockListViewModel {

    private val _viewState = MutableLiveData<ViewState>(ViewState.Loading)
    override val viewState : LiveData<ViewState> = _viewState

    override fun onAddButtonPress() {
        navigator.navigateTo(Destination.AddStock)
    }

    init {
        getStockList()
    }

    private fun getStockList() {
        viewModelScope.launch {
            when (val result = getStockList(GetStockList.Params)) {
                is Either.Left -> {
                    Timber.e(result.value)
                    _viewState.postValue(ViewState.Error(::getStockList))
                }
                is Either.Right -> {
                    _viewState.postValue(
                        ViewState.Result(
                        list = result.value.items
                    ))
                }
            }
        }
    }
}

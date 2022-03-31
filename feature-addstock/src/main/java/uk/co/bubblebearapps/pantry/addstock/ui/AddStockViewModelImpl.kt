package uk.co.bubblebearapps.pantry.addstock.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uk.co.bubblebearapps.pantry.addstock.domain.AddStockUseCase
import uk.co.bubblebearapps.pantry.addstock.ui.AddStockViewModel.ViewState
import uk.co.bubblebearapps.pantry.domain.Destination
import uk.co.bubblebearapps.pantry.domain.Navigator
import javax.inject.Inject

@HiltViewModel
class AddStockViewModelImpl @Inject constructor(
    private val navigator: Navigator,
    private val addStock: AddStockUseCase,
) : AddStockViewModel, ViewModel(){

    override val viewState = MutableLiveData<ViewState>(ViewState.Idle)

    override fun onItemAdded(itemName: String) {
        viewModelScope.launch {
            viewState.value = ViewState.Loading

            addStock(AddStockUseCase.Params(itemName))
                .fold(
                    {/* error */
                        viewState.value = ViewState.Error
                    },
                    {/* success */
                        navigator.navigateTo(Destination.StockList)
                    }
                )
        }
    }
}
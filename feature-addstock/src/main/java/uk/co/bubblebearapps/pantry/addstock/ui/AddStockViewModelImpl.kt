package uk.co.bubblebearapps.pantry.addstock.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.flatMap
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uk.co.bubblebearapps.pantry.addstock.domain.AddStockUseCase
import uk.co.bubblebearapps.pantry.addstock.domain.GetStockSnapshotUseCase
import uk.co.bubblebearapps.pantry.addstock.domain.UpdateStockUseCase
import uk.co.bubblebearapps.pantry.addstock.ui.AddStockViewModel.ViewState
import uk.co.bubblebearapps.pantry.domain.model.StockId
import uk.co.bubblebearapps.pantry.domain.model.UnitOfMeasure
import javax.inject.Inject

@HiltViewModel
internal class AddStockViewModelImpl @Inject constructor(
    private val addStock: AddStockUseCase,
    private val getStockSnapshot: GetStockSnapshotUseCase,
    private val updateStock: UpdateStockUseCase,
) : AddStockViewModel, ViewModel() {

    override val viewState = MutableLiveData<ViewState>(ViewState.NameEntry)

    override fun onNameEntered(itemName: String) {
        viewModelScope.launch {
            viewState.value = ViewState.Loading
            addStock(itemName)
                .flatMap { getStockSnapshot(it) }
                .fold(
                    {/* error */
                        viewState.value = ViewState.Error
                    },
                    { /* success */ stock ->
                        viewState.value = ViewState.QuantityEntry(
                            itemName = stock.name,
                            unitOfMeasure = stock.unitOfMeasure,
                            quantity = stock.quantity,
                            onConfirmedAction = { unitOfMeasure : UnitOfMeasure, quantity: Int ->
                                onQuantityConfirmed(stock.id, unitOfMeasure, quantity)
                            }
                        )

                    }
                )
        }
    }


    private fun onQuantityConfirmed(
        stockId: StockId,
        unitOfMeasure: UnitOfMeasure,
        quantity: Int
    ) {
        viewModelScope.launch {
            viewState.value = ViewState.Loading
            updateStock(UpdateStockUseCase.Params(stockId, unitOfMeasure, quantity))
                .fold(
                    {/* error */
                        viewState.value = ViewState.Error
                    },
                    { /* success */
                        viewState.value = ViewState.Complete
                    }
                )
        }
    }
}
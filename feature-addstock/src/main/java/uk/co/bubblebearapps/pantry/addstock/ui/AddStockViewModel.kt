package uk.co.bubblebearapps.pantry.addstock.ui

import androidx.lifecycle.LiveData
import uk.co.bubblebearapps.pantry.domain.model.UnitOfMeasure

internal interface AddStockViewModel {

    val viewState: LiveData<ViewState>

    fun onNameEntered(itemName: String)

    sealed class ViewState {
        object NameEntry : ViewState()

        data class QuantityEntry(
            val itemName: String,
            val unitOfMeasure: UnitOfMeasure,
            val quantity: Int,
            val onConfirmedAction: (UnitOfMeasure, Int) -> Unit
        ) : ViewState()

        object Loading : ViewState()
        object Error : ViewState()
        object Complete : ViewState()
    }
}

package uk.co.bubblebearapps.pantry.shoppinglist.ui

import androidx.lifecycle.LiveData
import uk.co.bubblebearapps.pantry.shoppinglist.domain.model.ShoppingListItem

internal interface ShoppingListViewModel {

    val viewState: LiveData<ViewState>

    sealed class ViewState {
        object Empty : ViewState()

        data class Result(
            val list: List<ShoppingListItem>,
        ) : ViewState()
    }
}
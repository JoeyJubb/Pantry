package uk.co.bubblebearapps.pantry.shoppinglist.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uk.co.bubblebearapps.pantry.shoppinglist.domain.GetShoppingList
import uk.co.bubblebearapps.pantry.shoppinglist.domain.model.ShoppingListItem
import uk.co.bubblebearapps.pantry.shoppinglist.ui.ShoppingListViewModel.ViewState
import javax.inject.Inject

@HiltViewModel
internal class ShoppingListViewModelImpl @Inject constructor(
    private val getShoppingList: GetShoppingList,
) : ViewModel(), ShoppingListViewModel {

    override val viewState = MutableLiveData<ViewState>(ViewState.Empty)

    init {
        viewModelScope.launch {
            getShoppingList(Unit)
                .collect(::onStockListUpdated)
        }
    }

    private fun onStockListUpdated(shoppingList: List<ShoppingListItem>) {
        viewState.value = if (shoppingList.isNotEmpty()) {
            ViewState.Result(list = shoppingList)
        } else {
            ViewState.Empty
        }
    }
}

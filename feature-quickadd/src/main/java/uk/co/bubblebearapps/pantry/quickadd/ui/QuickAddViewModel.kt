package uk.co.bubblebearapps.pantry.quickadd.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uk.co.bubblebearapps.pantry.quickadd.domain.AddToShoppingList
import javax.inject.Inject

@HiltViewModel
class QuickAddViewModel @Inject constructor(
    private val addToShoppingList: AddToShoppingList,
) : ViewModel() {

    fun onProductNameEntered(productName: String) {
        productName
            .takeIf { it.isNotEmpty() }
            ?.let {
                viewModelScope.launch {
                    addToShoppingList(it)
                }
            }
    }
}

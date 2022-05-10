package uk.co.bubblebearapps.pantry.shoppinglist.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uk.co.bubblebearapps.pantry.data.PantryRepository
import uk.co.bubblebearapps.pantry.domain.FlowUseCase
import uk.co.bubblebearapps.pantry.shoppinglist.domain.model.ShoppingListItem
import uk.co.bubblebearapps.pantry.ext.mapItems
import javax.inject.Inject

internal class GetShoppingList @Inject constructor(
    private val repository: PantryRepository,
) : FlowUseCase<Unit, List<ShoppingListItem>>() {

    override fun invoke(params: Unit): Flow<List<ShoppingListItem>> {
        return repository.getShoppingList()
            .map { it.asReversed() }
            .mapItems { stock ->
                ShoppingListItem(
                    id = stock.id,
                    name = stock.name,
                    unitOfMeasure = stock.unitOfMeasure,
                    quantity = stock.quantity
                )
            }
    }
}
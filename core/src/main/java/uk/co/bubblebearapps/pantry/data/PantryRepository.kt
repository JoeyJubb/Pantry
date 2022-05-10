package uk.co.bubblebearapps.pantry.data

import kotlinx.coroutines.flow.Flow
import uk.co.bubblebearapps.pantry.domain.Item
import uk.co.bubblebearapps.pantry.domain.ItemId
import uk.co.bubblebearapps.pantry.domain.UnitOfMeasure

interface PantryRepository {

    fun getShoppingList(): Flow<List<Item>>

    suspend fun addToShoppingList(name: String): ItemId

    suspend fun updateShoppingListItem(itemId: ItemId, unitOfMeasure: UnitOfMeasure, quantity: Int)
}

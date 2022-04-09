package uk.co.bubblebearapps.pantry.data

import kotlinx.coroutines.flow.Flow
import uk.co.bubblebearapps.pantry.domain.model.Item
import uk.co.bubblebearapps.pantry.domain.model.Stock

interface PantryRepository {

    fun getStock(): Flow<List<Stock>>

    /**
     * Known errors: AlreadyAddedException when the item already exists in the database
     */
    suspend fun addItem(itemName: String): Result<Item>
}

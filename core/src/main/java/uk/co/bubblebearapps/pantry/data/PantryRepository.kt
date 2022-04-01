package uk.co.bubblebearapps.pantry.data

import kotlinx.coroutines.flow.Flow
import uk.co.bubblebearapps.pantry.domain.model.Stock

interface PantryRepository {

    fun getStock(): Flow<List<Stock>>

    suspend fun addStock(itemName: String): Stock
}

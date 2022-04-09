package uk.co.bubblebearapps.pantry.data

import kotlinx.coroutines.flow.Flow
import uk.co.bubblebearapps.pantry.domain.model.Stock
import uk.co.bubblebearapps.pantry.domain.model.StockId
import uk.co.bubblebearapps.pantry.domain.model.UnitOfMeasure

interface PantryRepository {

    fun getStock(): Flow<List<Stock>>

    fun getStock(stockId: StockId) : Flow<Stock>

    suspend fun newStock(name: String): StockId

    suspend fun updateStock(stockId: StockId, unitOfMeasure: UnitOfMeasure, quantity: Int)
}

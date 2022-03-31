package uk.co.bubblebearapps.pantry.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import uk.co.bubblebearapps.pantry.domain.model.Stock
import javax.inject.Inject

class InMemoryPantryRepository @Inject constructor(

): PantryRepository {

    private val stockList = mutableListOf<Stock>()

    private val stockFlow = MutableSharedFlow<List<Stock>>(replay = 1)

    override fun getStock(): Flow<List<Stock>> = stockFlow

    override suspend fun addStock(itemName: String): Stock =
            stockList.find { it.name == itemName }
                ?: Stock(itemName).apply { addToList(this) }


    private suspend fun addToList(stock: Stock) {
        stockList.add(stock)
        stockFlow.emit(stockList)
    }
}

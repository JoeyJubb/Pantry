package uk.co.bubblebearapps.pantry.data

import uk.co.bubblebearapps.pantry.domain.model.Stock
import javax.inject.Inject

class InMemoryPantryRepository @Inject constructor(

): PantryRepository {

    private val stockList = mutableListOf<Stock>()

    override suspend fun getStock() = stockList

    override fun addStock(itemName: String): Stock =
        stockList.find { it.name == itemName }
            ?: Stock(itemName).apply { stockList.add(this) }
}

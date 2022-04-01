package uk.co.bubblebearapps.pantry.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import uk.co.bubblebearapps.pantry.domain.model.Stock
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject

class InMemoryPantryRepository @Inject constructor(

) : PantryRepository {

    private val stockList = mutableListOf<Stock>()
    private val stockFlow = MutableSharedFlow<List<Stock>>(replay = 1)

    private val atomicInteger = AtomicInteger()

    override fun getStock(): Flow<List<Stock>> = stockFlow

    override suspend fun addStock(itemName: String): Stock =
        stockList.find { it.name == itemName }
            ?: Stock(
                id = generateId(),
                name = itemName
            ).apply { addToList(this) }

    private fun generateId(): String = atomicInteger.getAndIncrement().toString()

    private suspend fun addToList(stock: Stock) {
        stockList.add(stock)
        stockFlow.emit(stockList)
    }
}

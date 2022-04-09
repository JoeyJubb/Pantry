package uk.co.bubblebearapps.pantry.data

import kotlinx.coroutines.flow.*
import uk.co.bubblebearapps.pantry.data.exceptions.StockNotFoundException
import uk.co.bubblebearapps.pantry.domain.model.Stock
import uk.co.bubblebearapps.pantry.domain.model.StockId
import uk.co.bubblebearapps.pantry.domain.model.UnitOfMeasure
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject

class InMemoryPantryRepository @Inject constructor(

) : PantryRepository {

    private val items = mutableListOf<Stock>()

    private val stockUpdateFlow = MutableSharedFlow<StockId>(replay = 1)

    private val atomicInteger = AtomicInteger()

    override fun getStock(): Flow<List<Stock>> = stockUpdateFlow.map { items }

    override fun getStock(stockId: StockId): Flow<Stock> = stockUpdateFlow
        .filter { it == stockId }
        .mapNotNull { findStockById(stockId) }
        .onStart {
            findStockById(stockId)
                ?.let { emit(it) }
        }

    override suspend fun newStock(
        name: String,
    ): StockId =
        (findStockByName(name) ?: addStock(name))
        .id


    private suspend fun addStock(name: String) =
        Stock(
            id = generateId(),
            name = name,
            unitOfMeasure = UnitOfMeasure.UNITS,
            quantity = 0,
        ).also { stock ->
            items.add(stock)
            stockUpdateFlow.emit(stock.id)
        }

    override suspend fun updateStock(
        stockId: StockId,
        unitOfMeasure: UnitOfMeasure,
        quantity: Int
    ) {
        val existingStock = findStockById(stockId)

        if(existingStock == null){
            throw StockNotFoundException("Cannot find stock with id $stockId")
        }else{
            items.remove(existingStock)
            items.add(existingStock.copy(unitOfMeasure = unitOfMeasure, quantity = quantity))
            stockUpdateFlow.emit(stockId)
        }
    }


    private fun findStockById(stockId: StockId) = items.find { it.id == stockId }
    private fun findStockByName(name: String) = items.find { it.name == name }
    private fun generateId(): String = atomicInteger.getAndIncrement().toString()
}

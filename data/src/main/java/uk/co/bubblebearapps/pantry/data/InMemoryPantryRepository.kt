package uk.co.bubblebearapps.pantry.data

import kotlinx.coroutines.flow.*
import uk.co.bubblebearapps.pantry.data.exceptions.StockNotFoundException
import uk.co.bubblebearapps.pantry.domain.Item
import uk.co.bubblebearapps.pantry.domain.ItemId
import uk.co.bubblebearapps.pantry.domain.UnitOfMeasure
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject

class InMemoryPantryRepository @Inject constructor(

) : PantryRepository {

    private val items = mutableListOf<Item>()

    private val stockUpdateFlow = MutableSharedFlow<ItemId>(replay = 1)

    private val atomicInteger = AtomicInteger()

    override fun getShoppingList(): Flow<List<Item>> = stockUpdateFlow.map { items }

    override suspend fun newIngredient(
        name: String,
    ): ItemId =
        (findStockByName(name) ?: addStock(name))
        .id


    private suspend fun addStock(name: String) =
        Item(
            id = generateId(),
            name = name,
            unitOfMeasure = UnitOfMeasure.UNITS,
            quantity = 0,
        ).also { stock ->
            items.add(stock)
            stockUpdateFlow.emit(stock.id)
        }

    override suspend fun updateIngredient(
        itemId: ItemId,
        unitOfMeasure: UnitOfMeasure,
        quantity: Int
    ) {
        val existingStock = findStockById(itemId)

        if(existingStock == null){
            throw StockNotFoundException("Cannot find stock with id $itemId")
        }else{
            items.remove(existingStock)
            items.add(existingStock.copy(unitOfMeasure = unitOfMeasure, quantity = quantity))
            stockUpdateFlow.emit(itemId)
        }
    }


    private fun findStockById(itemId: ItemId) = items.find { it.id == itemId }
    private fun findStockByName(name: String) = items.find { it.name == name }
    private fun generateId(): String = atomicInteger.getAndIncrement().toString()
}

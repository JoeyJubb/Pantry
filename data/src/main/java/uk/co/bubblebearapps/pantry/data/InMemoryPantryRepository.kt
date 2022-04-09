package uk.co.bubblebearapps.pantry.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map
import uk.co.bubblebearapps.pantry.domain.model.Item
import uk.co.bubblebearapps.pantry.domain.model.Stock
import uk.co.bubblebearapps.pantry.domain.model.UnitOfMeasure
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject

class InMemoryPantryRepository @Inject constructor(

) : PantryRepository {

    private val items = mutableMapOf<Item, Int>()

    private val stockUpdateFlow = MutableSharedFlow<Unit>(replay = 1)

    private val atomicInteger = AtomicInteger()

    override fun getStock(): Flow<List<Stock>> = stockUpdateFlow.map {
        items.map {
            Stock(
                item = it.key,
                quantity = it.value,
            )
        }
    }

    override suspend fun addItem(
        itemName: String,
    ): Result<Item> =
        items.keys
            .find { it.name == itemName }
            ?.let { existingItem -> Result.success(existingItem) }
            ?: Item(
                id = generateId(),
                name = itemName,
                unitOfMeasure = UnitOfMeasure.MASS_GRAMS
            ).also { item ->
                items[item] = 0
                stockUpdateFlow.emit(Unit)
            }.let { Result.success(it) }

    private fun generateId(): String = atomicInteger.getAndIncrement().toString()
}

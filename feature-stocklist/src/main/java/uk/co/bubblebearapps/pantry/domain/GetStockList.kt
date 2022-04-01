package uk.co.bubblebearapps.pantry.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uk.co.bubblebearapps.pantry.data.PantryRepository
import uk.co.bubblebearapps.pantry.domain.model.StockListItem
import uk.co.bubblebearapps.pantry.ext.mapItems
import javax.inject.Inject

internal class GetStockList @Inject constructor(
    private val repository: PantryRepository,
) : FlowUseCase<Unit, List<StockListItem>>() {

    object Params

    override fun invoke(params: Unit): Flow<List<StockListItem>> {
        return repository.getStock()
            .map { it.asReversed() }
            .mapItems { stock ->
                StockListItem(
                    id = stock.id,
                    name = stock.name
                )
            }
    }
}
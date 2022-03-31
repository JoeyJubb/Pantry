package uk.co.bubblebearapps.pantry.domain

import uk.co.bubblebearapps.pantry.data.PantryRepository
import uk.co.bubblebearapps.pantry.domain.model.StockListItem
import javax.inject.Inject

internal class GetStockList @Inject constructor(
    private val repository: PantryRepository,
) : UseCase<GetStockList.Params, GetStockList.Result>() {

    object Params

    data class Result(
        val items: List<StockListItem>,
    )

    override suspend fun doWork(params: Params): Result =
        repository.getStock()
            .map { stock ->
                StockListItem(name = stock.name)
            }
            .let { pantryItems -> Result(pantryItems) }
}
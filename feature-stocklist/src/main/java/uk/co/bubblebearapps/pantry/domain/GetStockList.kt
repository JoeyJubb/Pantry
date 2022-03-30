package uk.co.bubblebearapps.pantry.domain

import arrow.core.Either
import uk.co.bubblebearapps.pantry.data.PantryRepository
import uk.co.bubblebearapps.pantry.domain.model.StockListItem
import uk.co.bubblebearapps.pantry.ext.mapItems
import javax.inject.Inject

internal class GetStockList @Inject constructor(
    private val repository: PantryRepository,
) : UseCase<GetStockList.Params, GetStockList.Result> {

    object Params

    data class Result(
        val items: List<StockListItem>,
    )

    override suspend fun invoke(params: Params): Either<Throwable, Result> =
        repository.getStock()
            .mapItems{ stock ->
                StockListItem(name = stock.name)
            }
            .map { pantryItems -> Result(pantryItems) }
}
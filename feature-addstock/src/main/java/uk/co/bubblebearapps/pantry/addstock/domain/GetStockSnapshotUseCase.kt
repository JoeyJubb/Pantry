package uk.co.bubblebearapps.pantry.addstock.domain

import kotlinx.coroutines.flow.first
import uk.co.bubblebearapps.pantry.data.PantryRepository
import uk.co.bubblebearapps.pantry.domain.UseCase
import uk.co.bubblebearapps.pantry.domain.model.StockId
import uk.co.bubblebearapps.pantry.domain.model.Stock
import javax.inject.Inject

internal class GetStockSnapshotUseCase @Inject constructor(
    private val pantryRepository: PantryRepository,
) : UseCase<StockId, Stock>() {

    override suspend fun doWork(params: StockId): Stock =
        pantryRepository.getStock(stockId = params)
            .first()

}

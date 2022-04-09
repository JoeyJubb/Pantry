package uk.co.bubblebearapps.pantry.addstock.domain

import uk.co.bubblebearapps.pantry.data.PantryRepository
import uk.co.bubblebearapps.pantry.domain.UseCase
import uk.co.bubblebearapps.pantry.domain.model.StockId
import javax.inject.Inject

internal class AddStockUseCase @Inject constructor(
    private val pantryRepository: PantryRepository,
) : UseCase<String, StockId>() {

    override suspend fun doWork(params: String): StockId =
        pantryRepository.newStock(
            name = params
        )
}
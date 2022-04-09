package uk.co.bubblebearapps.pantry.addstock.domain

import uk.co.bubblebearapps.pantry.data.PantryRepository
import uk.co.bubblebearapps.pantry.domain.UseCase
import uk.co.bubblebearapps.pantry.domain.model.StockId
import uk.co.bubblebearapps.pantry.domain.model.UnitOfMeasure
import javax.inject.Inject

internal class UpdateStockUseCase @Inject constructor(
    private val pantryRepository: PantryRepository,
) : UseCase<UpdateStockUseCase.Params, Unit>() {

    data class Params(val stockId: StockId,
                      val unitOfMeasure: UnitOfMeasure,
                      val quantity: Int,
    )

    override suspend fun doWork(params: Params) {
        pantryRepository.updateStock(
            params.stockId,
            params.unitOfMeasure,
            params.quantity,
        )
    }

}
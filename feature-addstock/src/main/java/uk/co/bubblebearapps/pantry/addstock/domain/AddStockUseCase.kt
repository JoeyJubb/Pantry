package uk.co.bubblebearapps.pantry.addstock.domain

import uk.co.bubblebearapps.pantry.data.PantryRepository
import uk.co.bubblebearapps.pantry.domain.UseCase
import javax.inject.Inject

class AddStockUseCase @Inject constructor(
    private val pantryRepository: PantryRepository,
) : UseCase<AddStockUseCase.Params, Unit>() {

    data class Params(val itemName: String)

    override suspend fun doWork(params: Params): Unit =
        pantryRepository.addStock(params.itemName)
            .let { }

}
package uk.co.bubblebearapps.pantry.addstock.domain

import uk.co.bubblebearapps.pantry.data.PantryRepository
import uk.co.bubblebearapps.pantry.domain.UseCase
import javax.inject.Inject

internal class AddStockUseCase @Inject constructor(
    private val pantryRepository: PantryRepository,
) : UseCase<String, Unit>() {

    override suspend fun doWork(params: String): Unit =
        pantryRepository.addStock(params)
            .let { }

}
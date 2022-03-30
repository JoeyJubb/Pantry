package uk.co.bubblebearapps.pantry.data

import arrow.core.Either
import uk.co.bubblebearapps.pantry.domain.model.Stock

interface PantryRepository {

    suspend fun getStock() : Either<PantryRepoError, List<Stock>>
}

class PantryRepoError(override val cause: Throwable?) : Throwable(cause)
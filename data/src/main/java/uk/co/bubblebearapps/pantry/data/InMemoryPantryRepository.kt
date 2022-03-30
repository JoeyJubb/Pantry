package uk.co.bubblebearapps.pantry.data

import arrow.core.Either
import uk.co.bubblebearapps.pantry.domain.model.Stock
import javax.inject.Inject

class InMemoryPantryRepository @Inject constructor(

): PantryRepository {

    override suspend fun getStock(): Either<PantryRepoError, List<Stock>> =
        Either.Right(
            listOf(
                Stock("One"),
                Stock("Two"),
                Stock("Three"),
                Stock("Four"),
                Stock("Five"),
            )
        )
}
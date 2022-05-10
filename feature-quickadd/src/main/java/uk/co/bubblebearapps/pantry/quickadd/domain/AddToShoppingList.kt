package uk.co.bubblebearapps.pantry.quickadd.domain

import uk.co.bubblebearapps.pantry.data.PantryRepository
import uk.co.bubblebearapps.pantry.domain.UseCase
import javax.inject.Inject

class AddToShoppingList @Inject constructor(
    private val pantryRepository: PantryRepository,
) : UseCase<String, Unit>() {

    override suspend fun doWork(params: String) = pantryRepository.addToShoppingList(params).let {  }
}

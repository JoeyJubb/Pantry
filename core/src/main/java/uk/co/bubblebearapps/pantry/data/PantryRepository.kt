package uk.co.bubblebearapps.pantry.data

import uk.co.bubblebearapps.pantry.domain.model.Stock

interface PantryRepository {

    suspend fun getStock() : List<Stock>
}

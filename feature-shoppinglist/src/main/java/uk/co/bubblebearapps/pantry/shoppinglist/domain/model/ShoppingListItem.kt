package uk.co.bubblebearapps.pantry.shoppinglist.domain.model

import uk.co.bubblebearapps.pantry.domain.UnitOfMeasure

internal data class ShoppingListItem(
    val id: String,
    val name: String,
    val quantity: Int,
    val unitOfMeasure: UnitOfMeasure,
)
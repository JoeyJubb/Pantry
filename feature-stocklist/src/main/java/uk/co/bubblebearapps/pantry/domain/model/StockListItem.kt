package uk.co.bubblebearapps.pantry.domain.model

internal data class StockListItem(
    val id: String,
    val name: String,
    val quantity: Int,
    val unitOfMeasure: UnitOfMeasure,
)
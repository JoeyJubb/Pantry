package uk.co.bubblebearapps.pantry.domain.model

typealias StockId = String

data class Stock(
    val id: StockId,
    val name: String,
    val unitOfMeasure: UnitOfMeasure,
    val quantity: Int,
)
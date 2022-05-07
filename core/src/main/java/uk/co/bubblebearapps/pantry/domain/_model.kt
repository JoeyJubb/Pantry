package uk.co.bubblebearapps.pantry.domain

enum class UnitOfMeasure {
    UNITS,
    MASS_GRAMS,
    VOLUME_MILLILITERS,
    LENGTH_MILLIMETERS,
}

typealias ItemId = String

data class Item(
    val id: ItemId,
    val name: String,
    val unitOfMeasure: UnitOfMeasure,
    val quantity: Int,
)

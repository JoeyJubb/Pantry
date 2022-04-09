package uk.co.bubblebearapps.pantry.domain.model

data class Item(
    val id: String,
    val name: String,
    val unitOfMeasure: UnitOfMeasure,
)
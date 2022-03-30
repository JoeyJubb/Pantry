package uk.co.bubblebearapps.pantry.domain

sealed class Destination {

    object StockList : Destination()

    object AddStock: Destination()

}

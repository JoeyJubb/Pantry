package uk.co.bubblebearapps.pantry.ext

fun <T, R> equals(
    oldItem: T,
    newItem: T,
    vararg selector: (T) -> R
) = selector.all {
    it(oldItem) == it(newItem)
}
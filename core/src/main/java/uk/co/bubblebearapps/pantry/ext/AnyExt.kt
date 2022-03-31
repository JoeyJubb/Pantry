package uk.co.bubblebearapps.pantry.ext

fun <T, R> equals(
    oldItem: T,
    newItem: T,
    selector: (T) -> R
) = selector(oldItem) == selector(newItem)
package uk.co.bubblebearapps.pantry.ext


fun Int.isIn(intRange: IntRange): Boolean = intRange.contains(this)

fun Int.isNotIn(intRange: IntRange): Boolean = !isIn(intRange)


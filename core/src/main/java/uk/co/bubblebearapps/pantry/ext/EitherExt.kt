package uk.co.bubblebearapps.pantry.ext

import arrow.core.Either


/**
 *  The given function is applied if this is a Right.
 *  Example:
 *  Right(listOf(12,13,14)).mapItems { "flower" } // Result: Right(listOf("flower", "flower", "flower"))
 *  Left(listOf(12,13,14)).mapItems { "flower" }  // Result: Left(12)
 */
fun <T, R, E> Either<E, List<T>>.mapItems(function: (T) -> R): Either<E, List<R>> =
    map { list -> list.map{ item -> function(item)} }
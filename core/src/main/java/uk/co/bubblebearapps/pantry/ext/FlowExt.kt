package uk.co.bubblebearapps.pantry.ext

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


/**
 * Convenience function for mapping the items of a flow that emits a list
 */
fun <T, R> Flow<List<T>>.mapItems(function: (T) -> R): Flow<List<R>> =
    map { list -> list.map { item -> function(item) } }
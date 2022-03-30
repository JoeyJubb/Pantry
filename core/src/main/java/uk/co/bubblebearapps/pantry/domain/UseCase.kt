package uk.co.bubblebearapps.pantry.domain

import arrow.core.Either

interface UseCase<Params, Result> {

    suspend fun invoke(params: Params): Either<Throwable, Result>

}

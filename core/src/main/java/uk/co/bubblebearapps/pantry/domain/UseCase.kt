package uk.co.bubblebearapps.pantry.domain

import arrow.core.Either
import timber.log.Timber

abstract class UseCase<Params, Result> {

    suspend operator fun invoke(params: Params): Either<Throwable, Result> =
        kotlin.runCatching { doWork(params) }
            .fold(
                { result -> Either.Right(result) },
                { throwable ->
                    Timber.e(throwable)
                    Either.Left(throwable)
                }
            )

    protected abstract suspend fun doWork(params: Params): Result

}

package uk.co.bubblebearapps.pantry.domain

import kotlinx.coroutines.flow.Flow

abstract class FlowUseCase<Params, Result> {

    abstract operator fun invoke(params: Params): Flow<Result>

}

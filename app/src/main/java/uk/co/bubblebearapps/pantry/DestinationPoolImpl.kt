package uk.co.bubblebearapps.pantry

import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import uk.co.bubblebearapps.pantry.domain.Destination
import uk.co.bubblebearapps.pantry.domain.Navigator
import javax.inject.Inject

@ActivityRetainedScoped
class DestinationPoolImpl @Inject constructor(

): DestinationPool, Navigator {

    private val _destinations: Channel<Destination> = Channel(Channel.BUFFERED)
    override val destinations: Flow<Destination> = _destinations.receiveAsFlow()

    init {
        navigateTo(Destination.StockList)
    }

    override fun navigateTo(destination: Destination) {
        _destinations.trySend(destination)
    }
}

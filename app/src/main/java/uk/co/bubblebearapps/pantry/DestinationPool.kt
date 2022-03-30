package uk.co.bubblebearapps.pantry

import kotlinx.coroutines.flow.Flow
import uk.co.bubblebearapps.pantry.domain.Destination

interface DestinationPool {

    val destinations : Flow<Destination>

}

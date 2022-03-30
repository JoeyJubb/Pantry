package uk.co.bubblebearapps.pantry.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import uk.co.bubblebearapps.pantry.DestinationPool
import uk.co.bubblebearapps.pantry.DestinationPoolImpl
import uk.co.bubblebearapps.pantry.domain.Navigator

@Module
@InstallIn(ActivityRetainedComponent::class)
internal interface NavigatorModule {

    @Binds
    fun navigator(impl: DestinationPoolImpl): Navigator

    @Binds
    fun destinationPool(impl: DestinationPoolImpl): DestinationPool
}

package uk.co.bubblebearapps.pantry.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import uk.co.bubblebearapps.pantry.app.TimberInitializer
import uk.co.bubblebearapps.pantry.domain.model.AppInitializer

@Module
@InstallIn(SingletonComponent::class)
internal interface AppModule {

    @Binds
    @IntoSet
    fun timber(impl: TimberInitializer): AppInitializer

}
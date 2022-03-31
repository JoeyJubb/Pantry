package uk.co.bubblebearapps.pantry.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import org.threeten.bp.Clock
import uk.co.bubblebearapps.pantry.domain.model.AppInitializer
import uk.co.bubblebearapps.pantry.app.ThreeTenInitializer
import uk.co.bubblebearapps.pantry.app.TimberInitializer
import javax.inject.Singleton
import kotlin.random.Random

@Module
@InstallIn(SingletonComponent::class)
internal interface AppModule {

    @Binds
    @IntoSet
    fun threeTen(impl: ThreeTenInitializer): AppInitializer

    @Binds
    @IntoSet
    fun timber(impl: TimberInitializer): AppInitializer

}

@Module
@InstallIn(SingletonComponent::class)
internal class ProvidesAppModule{

    @Provides
    @Singleton
    fun random(): Random = Random.Default

    @Provides
    @Singleton
    fun clock(): Clock = Clock.systemUTC()

}
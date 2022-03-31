package uk.co.bubblebearapps.pantry.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uk.co.bubblebearapps.pantry.data.InMemoryPantryRepository
import uk.co.bubblebearapps.pantry.data.PantryRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {

    @Binds
    @Singleton
    fun pantryRepo(impl: InMemoryPantryRepository): PantryRepository
}

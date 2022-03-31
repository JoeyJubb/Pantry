package uk.co.bubblebearapps.pantry.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import uk.co.bubblebearapps.pantry.addstock.domain.AddStockNavigator
import uk.co.bubblebearapps.pantry.domain.StockListNavigator
import uk.co.bubblebearapps.pantry.navigation.AddStockNavigatorImpl
import uk.co.bubblebearapps.pantry.navigation.StockListNavigatorImpl

@Module
@InstallIn(ActivityComponent::class)
internal interface NavigatorModule {

    @Binds
    fun addStockNavigator(impl: AddStockNavigatorImpl): AddStockNavigator

    @Binds
    fun stockListNavigator(impl: StockListNavigatorImpl): StockListNavigator
}

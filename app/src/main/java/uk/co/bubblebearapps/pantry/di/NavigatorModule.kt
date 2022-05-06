package uk.co.bubblebearapps.pantry.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import uk.co.bubblebearapps.pantry.addrecipe.domain.AddRecipeNavigator
import uk.co.bubblebearapps.pantry.navigation.Navigator
import uk.co.bubblebearapps.pantry.recipelist.domain.RecipeListNavigator
import uk.co.bubblebearapps.panty.shoppinglist.domain.ShoppingListNavigator

@Module
@InstallIn(FragmentComponent::class)
internal interface NavigatorModule {

    @Binds
    fun bindAddRecipeNavigator(impl: Navigator): AddRecipeNavigator

    @Binds
    fun bindRecipeListNavigator(impl: Navigator): RecipeListNavigator

    @Binds
    fun bindShoppingListNavigator(impl: Navigator): ShoppingListNavigator
}

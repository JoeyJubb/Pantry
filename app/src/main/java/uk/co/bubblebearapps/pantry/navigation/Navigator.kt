package uk.co.bubblebearapps.pantry.navigation

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import uk.co.bubblebearapps.pantry.R
import uk.co.bubblebearapps.pantry.addrecipe.domain.AddRecipeNavigator
import uk.co.bubblebearapps.pantry.addstock.domain.AddStockNavigator
import uk.co.bubblebearapps.pantry.addstock.ui.AddStockFragment
import uk.co.bubblebearapps.pantry.domain.StockListNavigator
import uk.co.bubblebearapps.pantry.recipelist.domain.RecipeListNavigator
import javax.inject.Inject

internal class Navigator @Inject constructor(
    private val activity: FragmentActivity,
) : AddStockNavigator,
    StockListNavigator,
    AddRecipeNavigator,
    RecipeListNavigator
{

    override fun closeAddStock() {
        activity
            .supportFragmentManager
            .popBackStack(BACK_STACK_ENTRY_ADD_STOCK, POP_BACK_STACK_INCLUSIVE)
    }

    override fun goToAddStock() {
        activity
            .supportFragmentManager
            .beginTransaction()
            .addToBackStack(BACK_STACK_ENTRY_ADD_STOCK)
            .replace(R.id.fragment_container_view, AddStockFragment.newInstance())
            .commit()
    }

    companion object {
        const val BACK_STACK_ENTRY_ADD_STOCK: String = "BACK_STACK_ENTRY_ADD_STOCK"
    }
}
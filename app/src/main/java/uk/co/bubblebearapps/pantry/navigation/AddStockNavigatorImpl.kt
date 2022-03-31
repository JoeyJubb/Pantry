package uk.co.bubblebearapps.pantry.navigation

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import uk.co.bubblebearapps.pantry.addstock.domain.AddStockNavigator
import uk.co.bubblebearapps.pantry.navigation.StockListNavigatorImpl.Companion.BACK_STACK_ENTRY_ADD_STOCK
import javax.inject.Inject

internal class AddStockNavigatorImpl @Inject constructor(
    private val activity: FragmentActivity,
) : AddStockNavigator {

    override fun backToStockList() {
        activity
            .supportFragmentManager
            .popBackStack(BACK_STACK_ENTRY_ADD_STOCK, POP_BACK_STACK_INCLUSIVE)
    }
}
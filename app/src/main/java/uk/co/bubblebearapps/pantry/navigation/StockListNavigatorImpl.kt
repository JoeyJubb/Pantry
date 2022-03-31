package uk.co.bubblebearapps.pantry.navigation

import androidx.fragment.app.FragmentActivity
import uk.co.bubblebearapps.pantry.R
import uk.co.bubblebearapps.pantry.addstock.ui.AddStockFragment
import uk.co.bubblebearapps.pantry.domain.StockListNavigator
import javax.inject.Inject

class StockListNavigatorImpl @Inject constructor(
    private val activity: FragmentActivity,
) : StockListNavigator {

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

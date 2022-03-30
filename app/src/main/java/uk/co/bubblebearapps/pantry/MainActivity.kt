package uk.co.bubblebearapps.pantry

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import uk.co.bubblebearapps.pantry.addstock.AddStockFragment
import uk.co.bubblebearapps.pantry.domain.Destination
import uk.co.bubblebearapps.pantry.ui.StockListFragment
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var destinationPool: DestinationPool

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            destinationPool
                .destinations
                .flowWithLifecycle(lifecycle, Lifecycle.State.RESUMED)
                .collect(::navigateTo)
        }
    }

    private fun navigateTo(destination: Destination) {
        val tag = destination.javaClass.name.toString()
        val fragment = supportFragmentManager.findFragmentByTag(tag) ?: createFragment(destination)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_view, fragment, tag)
            .apply {
                if (supportFragmentManager.fragments.size > 0) {
                    addToBackStack(null)
                }
            }
            .commit()
    }

    private fun createFragment(destination: Destination): Fragment =
        when (destination) {
            Destination.AddStock -> AddStockFragment.newInstance()
            Destination.StockList -> StockListFragment.newInstance()
        }
}

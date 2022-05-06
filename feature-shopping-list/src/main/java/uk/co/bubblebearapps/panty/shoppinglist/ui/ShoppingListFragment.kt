package uk.co.bubblebearapps.panty.shoppinglist.ui

import uk.co.bubblebearapps.panty.shoppinglist.domain.ShoppingListNavigator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import uk.co.bubblebearapps.pantry.shoppinglist.databinding.ShoppingListFragmentBinding
import javax.inject.Inject

@AndroidEntryPoint
class ShoppingListFragment : Fragment() {

    companion object {
        fun newInstance() = ShoppingListFragment()
    }

    @Inject
    lateinit var navigator: ShoppingListNavigator

    private lateinit var binding: ShoppingListFragmentBinding

    private val viewModel: ShoppingListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ShoppingListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}
package uk.co.bubblebearapps.pantry.shoppinglist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import uk.co.bubblebearapps.pantry.ext.observe
import uk.co.bubblebearapps.pantry.shoppinglist.databinding.ShoppingListFragmentBinding
import uk.co.bubblebearapps.pantry.shoppinglist.ui.ShoppingListViewModel.ViewState

@AndroidEntryPoint
class ShoppingListFragment : Fragment() {

    private lateinit var adapter: ShoppingListAdapter

    private lateinit var binding: ShoppingListFragmentBinding

    private val viewModel: ShoppingListViewModel by viewModels<ShoppingListViewModelImpl>()

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

        adapter = ShoppingListAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                RecyclerView.VERTICAL
            )
        )

        observe(viewModel.viewState, ::onViewStateChanged)
    }

    private fun onViewStateChanged(viewState: ViewState) {
        when (viewState) {
            is ViewState.Result -> adapter.items = viewState.list
            ViewState.Empty -> adapter.items = emptyList()
        }
    }

    companion object {
        fun newInstance() = ShoppingListFragment()
    }
}

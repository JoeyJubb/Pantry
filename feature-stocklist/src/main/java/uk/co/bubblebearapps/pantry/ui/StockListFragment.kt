package uk.co.bubblebearapps.pantry.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import uk.co.bubblebearapps.pantry.domain.StockListNavigator
import uk.co.bubblebearapps.pantry.ext.observe
import uk.co.bubblebearapps.pantry.stocklist.databinding.StockListFragmentBinding
import uk.co.bubblebearapps.pantry.ui.StockListViewModel.ViewState
import javax.inject.Inject

@AndroidEntryPoint
class StockListFragment : Fragment() {

    @Inject lateinit var stockListNavigator: StockListNavigator

    private lateinit var binding : StockListFragmentBinding
    private lateinit var adapter: StockListAdapter

    private val viewModel: StockListViewModel by viewModels<StockListViewModelImpl>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = StockListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = StockListAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))

        binding.floatingActionButton.setOnClickListener { viewModel.onAddButtonPress() }

        observe(viewModel.viewState, ::onViewStateChanged)
        observe(viewModel.events, ::onEvent)
    }

    private fun onViewStateChanged(viewState: ViewState) {
        when (viewState) {
            is ViewState.Result -> adapter.items = viewState.list
            ViewState.Empty -> {
                // TODO - show empty state
            }
        }
    }

    private fun onEvent(event: StockListViewModel.Event) {
        when(event){
            StockListViewModel.Event.GoToAddStock -> stockListNavigator.goToAddStock()
        }
    }

    companion object {
        fun newInstance() = StockListFragment()
    }
}

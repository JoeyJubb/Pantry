package uk.co.bubblebearapps.pantry.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import uk.co.bubblebearapps.pantry.ext.observe
import uk.co.bubblebearapps.pantry.stocklist.databinding.StockListFragmentBinding
import uk.co.bubblebearapps.pantry.ui.StockListViewModel.ViewState

@AndroidEntryPoint
class StockListFragment : Fragment() {

    companion object {
        fun newInstance() = StockListFragment()
    }

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
        binding.floatingActionButton.setOnClickListener { viewModel.onAddButtonPress() }

        observe(viewModel.viewState, ::onViewStateChanged)
    }

    private fun onViewStateChanged(viewState: ViewState) {
        binding.progress.hide()
        when (viewState) {
            is ViewState.Error -> Toast.makeText(requireContext(),
                "Error TODO",
                Toast.LENGTH_SHORT).show()
            is ViewState.Loading -> binding.progress.show()
            is ViewState.Result -> adapter.items = viewState.list
        }
    }
}

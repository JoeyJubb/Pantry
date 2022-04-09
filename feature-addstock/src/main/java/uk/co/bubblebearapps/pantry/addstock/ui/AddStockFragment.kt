package uk.co.bubblebearapps.pantry.addstock.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import uk.co.bubblebearapps.pantry.addstock.R
import uk.co.bubblebearapps.pantry.addstock.databinding.AddStockFragmentBinding
import uk.co.bubblebearapps.pantry.addstock.domain.AddStockNavigator
import uk.co.bubblebearapps.pantry.ext.observe
import javax.inject.Inject

@AndroidEntryPoint
class AddStockFragment : Fragment() {

    @Inject
    lateinit var navigator: AddStockNavigator

    private val viewModel: AddStockViewModel by viewModels<AddStockViewModelImpl>()

    private lateinit var binding: AddStockFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AddStockFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.setOnNameEnteredListener(viewModel::onNameEntered)

        observe(viewModel.viewState, ::onViewStateChange)
    }

    private fun onViewStateChange(viewState: AddStockViewModel.ViewState) = with(binding) {
        when (viewState) {
            is AddStockViewModel.ViewState.Error -> {
                showError()
            }
            AddStockViewModel.ViewState.NameEntry -> {
                showNameEntry()
            }
            is AddStockViewModel.ViewState.QuantityEntry -> {
                showQuantityEntry(viewState)
            }
            AddStockViewModel.ViewState.Loading -> {
                showLoading()
            }
            AddStockViewModel.ViewState.Complete -> {
                navigator.closeAddStock()
            }
        }
    }

    companion object {
        fun newInstance() = AddStockFragment()
    }
}

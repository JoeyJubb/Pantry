package uk.co.bubblebearapps.pantry.addstock.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import uk.co.bubblebearapps.pantry.addstock.R
import uk.co.bubblebearapps.pantry.addstock.databinding.AddStockFragmentBinding
import uk.co.bubblebearapps.pantry.addstock.domain.AddStockNavigator
import uk.co.bubblebearapps.pantry.ext.observe
import uk.co.bubblebearapps.pantry.ext.setOnImeActionListener
import uk.co.bubblebearapps.pantry.ext.showKeyboard
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

        binding.etItemName.setOnImeActionListener { onDoneButtonPress() }
        binding.btnAdd.setOnClickListener { onDoneButtonPress() }

        observe(viewModel.viewState, ::onViewStateChange)
    }

    private fun onViewStateChange(viewState: AddStockViewModel.ViewState) {
        showLoading(false)
        when (viewState) {
            AddStockViewModel.ViewState.Error -> {
                showError()
                showContent(true)
            }
            AddStockViewModel.ViewState.Idle -> {
                showContent(true)
            }
            AddStockViewModel.ViewState.Loading -> {
                showLoading(true)
                showContent(false)
            }
            AddStockViewModel.ViewState.Complete -> {
                navigator.closeAddStock()
            }
        }
    }

    private fun showError() {
        Toast.makeText(requireContext(), getString(R.string.err_add), Toast.LENGTH_SHORT).show()
    }

    private fun showContent(show: Boolean) {
        binding.groupInput.isVisible = show
        binding.etItemName.showKeyboard()
    }

    private fun showLoading(show: Boolean) {
        if (show) {
            binding.progress.show()
        } else {
            binding.progress.hide()
        }
    }

    private fun onDoneButtonPress() {
        val itemName = binding.etItemName.text.toString()

        if (itemName.isBlank()) {
            binding.etItemName.error = getString(R.string.err_required)
        } else {
            viewModel.onItemAdded(itemName)
        }
    }

    companion object {
        fun newInstance() = AddStockFragment()
    }
}
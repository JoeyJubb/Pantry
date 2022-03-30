package uk.co.bubblebearapps.pantry.addstock

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import uk.co.bubblebearapps.pantry.addstock.databinding.AddStockFragmentBinding

@AndroidEntryPoint
class AddStockFragment : Fragment() {

    companion object {
        fun newInstance() = AddStockFragment()
    }

    private val viewModel: AddStockViewModel by viewModels<AddStockViewModelImpl>()

    private lateinit var binding: AddStockFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AddStockFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}
package uk.co.bubblebearapps.pantry.addrecipe.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import uk.co.bubblebearapps.pantry.addrecipe.databinding.AddRecipeFragmentBinding
import uk.co.bubblebearapps.pantry.addrecipe.domain.AddRecipeNavigator
import javax.inject.Inject

@AndroidEntryPoint
class AddRecipeFragment : Fragment() {

    companion object {
        fun newInstance() = AddRecipeFragment()
    }

    @Inject
    lateinit var navigator: AddRecipeNavigator

    private lateinit var binding: AddRecipeFragmentBinding

    private val viewModel: AddRecipeViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AddRecipeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

}
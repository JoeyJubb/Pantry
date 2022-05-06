package uk.co.bubblebearapps.pantry.recipelist.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import uk.co.bubblebearapps.pantry.addrecipe.databinding.RecipeListFragmentBinding
import uk.co.bubblebearapps.pantry.recipelist.domain.RecipeListNavigator
import javax.inject.Inject

@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    companion object {
        fun newInstance() = RecipeListFragment()
    }

    @Inject
    lateinit var navigator: RecipeListNavigator

    private lateinit var binding: RecipeListFragmentBinding

    private val viewModel: RecipeListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RecipeListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

}
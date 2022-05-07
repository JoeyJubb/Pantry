package uk.co.bubblebearapps.pantry.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import uk.co.bubblebearapps.pantry.R
import uk.co.bubblebearapps.pantry.addrecipe.domain.AddRecipeNavigator
import uk.co.bubblebearapps.pantry.recipelist.domain.RecipeListNavigator
import uk.co.bubblebearapps.pantry.recipelist.ui.RecipeListFragmentDirections
import javax.inject.Inject

internal class Navigator @Inject constructor(
    private val fragment: Fragment,
) : AddRecipeNavigator,
    RecipeListNavigator
{

    override fun goToAddRecipe() {
        RecipeListFragmentDirections
            .actionRecipeListFragmentToAddRecipeFragment()
            .go()
    }

    override fun closeAddRecipe() {
        fragment.findNavController().popBackStack(
            R.id.addRecipeFragment, true
        )
    }

    private fun NavDirections.go() {
        fragment.findNavController()
            .navigate(this)
    }
}

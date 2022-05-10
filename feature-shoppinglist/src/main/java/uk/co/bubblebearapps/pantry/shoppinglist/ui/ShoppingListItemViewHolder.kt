package uk.co.bubblebearapps.pantry.shoppinglist.ui

import androidx.recyclerview.widget.RecyclerView
import uk.co.bubblebearapps.pantry.shoppinglist.domain.model.ShoppingListItem
import uk.co.bubblebearapps.pantry.domain.UnitOfMeasure
import uk.co.bubblebearapps.pantry.shoppinglist.R
import uk.co.bubblebearapps.pantry.shoppinglist.databinding.ShoppingListFragmentItemBinding

internal class ShoppingListItemViewHolder(private val binding: ShoppingListFragmentItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private val context = binding.root.context

    fun bind(entry: ShoppingListItem) {
        bindName(entry.name)
        bindQuantity(entry.quantity, entry.unitOfMeasure)
    }

    fun bind(changePayload: ShoppingListItemCallback.ChangePayload) {
        changePayload.name?.let { bindName(it) }

        if(changePayload.quantity != null && changePayload.unitOfMeasure != null){
            bindQuantity(changePayload.quantity, changePayload.unitOfMeasure)
        }
    }

    private fun bindName(name: String) {
        binding.textName.text = name
    }

    private fun bindQuantity(quantity: Int, unitOfMeasure: UnitOfMeasure) {
        binding.textQuantity.text = when(unitOfMeasure){
            UnitOfMeasure.UNITS -> quantity.toString()
            UnitOfMeasure.MASS_GRAMS -> context.getString(R.string.grams_x, quantity)
            UnitOfMeasure.VOLUME_MILLILITERS -> context.getString(R.string.milliliters_x, quantity)
            UnitOfMeasure.LENGTH_MILLIMETERS -> context.getString(R.string.millimeters_x, quantity)
        }
    }
}

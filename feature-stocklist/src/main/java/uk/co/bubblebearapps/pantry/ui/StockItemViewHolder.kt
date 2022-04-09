package uk.co.bubblebearapps.pantry.ui

import androidx.recyclerview.widget.RecyclerView
import uk.co.bubblebearapps.pantry.domain.model.StockListItem
import uk.co.bubblebearapps.pantry.domain.model.UnitOfMeasure
import uk.co.bubblebearapps.pantry.stocklist.R
import uk.co.bubblebearapps.pantry.stocklist.databinding.StockListFragmentItemBinding

internal class StockItemViewHolder(private val binding: StockListFragmentItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private val context = binding.root.context

    fun bind(entry: StockListItem) {
        bindName(entry.name)
        bindQuantity(entry.quantity, entry.unitOfMeasure)
    }

    fun bind(changePayload: StockListItemCallback.ChangePayload) {
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

package uk.co.bubblebearapps.pantry.ui

import androidx.recyclerview.widget.DiffUtil
import uk.co.bubblebearapps.pantry.domain.model.StockListItem
import uk.co.bubblebearapps.pantry.domain.model.UnitOfMeasure
import uk.co.bubblebearapps.pantry.ext.equals

internal class StockListItemCallback : DiffUtil.ItemCallback<StockListItem>() {

    override fun areItemsTheSame(oldItem: StockListItem, newItem: StockListItem): Boolean = equals(
        oldItem, newItem, { it.id }
    )

    override fun areContentsTheSame(oldItem: StockListItem, newItem: StockListItem): Boolean =
        equals(oldItem, newItem, { it.name }, { it.quantity })

    override fun getChangePayload(oldItem: StockListItem, newItem: StockListItem): ChangePayload {
        val name = if (!equals(oldItem, newItem, { it.name })) {
           newItem.name
        } else null

        val (quantity, unitOfMeasure) = if (!equals(oldItem, newItem, { it.quantity }, {it.unitOfMeasure})){
            newItem.quantity to newItem.unitOfMeasure
        } else null to null

        return ChangePayload(
            name, quantity, unitOfMeasure
        )
    }

    data class ChangePayload(
        val name: String? = null,
        val quantity: Int? = null,
        val unitOfMeasure: UnitOfMeasure? = null,
    )
}

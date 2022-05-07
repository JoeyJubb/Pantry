package uk.co.bubblebearapps.pantry.shoppinglist.ui

import androidx.recyclerview.widget.DiffUtil
import uk.co.bubblebearapps.pantry.shoppinglist.domain.model.ShoppingListItem
import uk.co.bubblebearapps.pantry.domain.UnitOfMeasure
import uk.co.bubblebearapps.pantry.ext.equals

internal class ShoppingListItemCallback : DiffUtil.ItemCallback<ShoppingListItem>() {

    override fun areItemsTheSame(oldItem: ShoppingListItem, newItem: ShoppingListItem): Boolean = equals(
        oldItem, newItem, { it.id }
    )

    override fun areContentsTheSame(oldItem: ShoppingListItem, newItem: ShoppingListItem): Boolean =
        equals(oldItem, newItem, { it.name }, { it.quantity })

    override fun getChangePayload(oldItem: ShoppingListItem, newItem: ShoppingListItem): ChangePayload {
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

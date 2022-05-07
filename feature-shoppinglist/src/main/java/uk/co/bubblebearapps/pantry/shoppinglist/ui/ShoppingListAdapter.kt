package uk.co.bubblebearapps.pantry.shoppinglist.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import uk.co.bubblebearapps.pantry.shoppinglist.domain.model.ShoppingListItem
import uk.co.bubblebearapps.pantry.shoppinglist.databinding.ShoppingListFragmentItemBinding

internal class ShoppingListAdapter : RecyclerView.Adapter<ShoppingListItemViewHolder>() {

    private val diffCallback = ShoppingListItemCallback()
    private val differ = AsyncListDiffer(this, diffCallback)

    var items: List<ShoppingListItem>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListItemViewHolder =
        ShoppingListFragmentItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ).let {
            ShoppingListItemViewHolder(it)
        }

    override fun onBindViewHolder(holder: ShoppingListItemViewHolder, position: Int) {
        holder.bind(getItemAt(position))
    }

    override fun onBindViewHolder(
        holder: ShoppingListItemViewHolder,
        position: Int,
        payloads: MutableList<Any>,
    ) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
        } else {
            payloads.forEach { payload ->
                holder.bind(payload as ShoppingListItemCallback.ChangePayload)
            }
        }
    }

    private fun getItemAt(position: Int): ShoppingListItem = items[position]

    override fun getItemCount(): Int = items.size
}
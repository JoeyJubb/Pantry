package uk.co.bubblebearapps.pantry.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import uk.co.bubblebearapps.pantry.domain.model.StockListItem
import uk.co.bubblebearapps.pantry.stocklist.databinding.StockListFragmentItemBinding

internal class StockListAdapter : RecyclerView.Adapter<StockItemViewHolder>() {

    private val diffCallback = StockListItemCallback()
    private val differ = AsyncListDiffer(this, diffCallback)

    var items: List<StockListItem>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockItemViewHolder =
        StockListFragmentItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ).let {
            StockItemViewHolder(it)
        }

    override fun onBindViewHolder(holder: StockItemViewHolder, position: Int) {
        holder.bind(getItemAt(position))
    }

    override fun onBindViewHolder(
        holder: StockItemViewHolder,
        position: Int,
        payloads: MutableList<Any>,
    ) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
        } else {
            payloads.forEach { payload ->
                holder.bind(payload as StockListItemCallback.ChangePayload)
            }
        }
    }

    private fun getItemAt(position: Int): StockListItem = items[position]

    override fun getItemCount(): Int = items.size
}
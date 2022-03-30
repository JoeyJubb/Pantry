package uk.co.bubblebearapps.pantry.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uk.co.bubblebearapps.pantry.domain.model.StockListItem
import uk.co.bubblebearapps.pantry.stocklist.databinding.StockListFragmentItemBinding

internal class StockListAdapter : RecyclerView.Adapter<StockListAdapter.StockItemViewHolder>() {

    var items: List<StockListItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class StockItemViewHolder(private val binding: StockListFragmentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(entry: StockListItem) {
            binding.textName.text = entry.name
        }
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

    private fun getItemAt(position: Int): StockListItem = items[position]

    override fun getItemCount(): Int = items.size
}
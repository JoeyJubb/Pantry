package uk.co.bubblebearapps.pantry.ui

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import uk.co.bubblebearapps.pantry.domain.model.StockListItem
import uk.co.bubblebearapps.pantry.stocklist.databinding.StockListFragmentItemBinding

class StockItemViewHolder(private val binding: StockListFragmentItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(entry: StockListItem) {
        bindName(entry.name)
    }

    fun bind(changePayload: Bundle) {
        changePayload.getString(StockListItemCallback.PAYLOAD_NAME)?.let { bindName(it) }
    }

    private fun bindName(name: String) {
        binding.textName.text = name
    }
}
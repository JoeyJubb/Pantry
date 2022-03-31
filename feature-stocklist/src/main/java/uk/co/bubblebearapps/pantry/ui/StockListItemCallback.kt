package uk.co.bubblebearapps.pantry.ui

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import uk.co.bubblebearapps.pantry.domain.model.StockListItem
import uk.co.bubblebearapps.pantry.ext.equals

class StockListItemCallback : DiffUtil.ItemCallback<StockListItem>() {

    override fun areItemsTheSame(oldItem: StockListItem, newItem: StockListItem): Boolean = equals(
        oldItem, newItem
    ) { it.id }

    override fun areContentsTheSame(oldItem: StockListItem, newItem: StockListItem): Boolean = equals(
        oldItem, newItem
    ) { it.name }

    override fun getChangePayload(oldItem: StockListItem, newItem: StockListItem): Bundle {
        val payload = Bundle()

        if (!equals(oldItem, newItem) { it.name }) {
            payload.putString(PAYLOAD_NAME, newItem.name)
        }
        return payload
    }

    companion object{
        const val PAYLOAD_NAME = "PAYLOAD_NAME"
    }
}

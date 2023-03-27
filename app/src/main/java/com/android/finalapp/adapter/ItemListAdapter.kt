package com.android.finalapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.finalapp.ItemRedeemActivity
import com.android.finalapp.R

class ItemListAdapter(
    private val itemList: List<com.android.finalapp.model.Item>
) : RecyclerView.Adapter<ItemListAdapter.ItemViewHolder>() {

    override fun getItemCount() = itemList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(view)
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemNameTextView: TextView = itemView.findViewById(R.id.itemName)
        private val itemPriceTextView: TextView = itemView.findViewById(R.id.itemPrice)
        private val itemDescTextView: TextView = itemView.findViewById(R.id.itemDescription)
        private val redeemTextView: TextView = itemView.findViewById(R.id.redeemTextView)

        fun bind(item: com.android.finalapp.model.Item) {
            itemNameTextView.text = item.itemName
            itemPriceTextView.text = "$${item.itemPrice}"
            itemDescTextView.text = item.itemDescription
            redeemTextView.text = "Redeem"

            redeemTextView.setOnClickListener {
                val intent = Intent(itemView.context, ItemRedeemActivity::class.java )
                intent.putExtra("item", item)
                itemView.context.startActivity(intent)
            }
        }
    }

}
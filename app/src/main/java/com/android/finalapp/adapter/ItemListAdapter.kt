package com.android.finalapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.map
import androidx.recyclerview.widget.RecyclerView
import com.android.finalapp.AddRatingActivity
import com.android.finalapp.ItemRedeemActivity
import com.android.finalapp.R
import com.android.finalapp.data.ItemDao
import com.android.finalapp.model.Rating
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ItemListAdapter(
    private val itemList: List<com.android.finalapp.model.Item>,
    private val itemDao: ItemDao
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
        private val itemImageView: ImageView = itemView.findViewById(R.id.itemImageRetrieved)
        private val rating: RatingBar = itemView.findViewById(R.id.itemRatingBar)
        private val itemComment: TextView = itemView.findViewById(R.id.itemCommentEditText)

        fun bind(item: com.android.finalapp.model.Item) {
            itemNameTextView.text = item.itemName
            itemPriceTextView.text = "$${item.itemPrice}"
            itemDescTextView.text = item.itemDescription
            redeemTextView.text = "Redeem"
            Glide.with(itemView.context)
                .load(item.imageUri)
                .apply(RequestOptions().placeholder(R.drawable.product_24))
                .timeout(6000)
                .into(itemImageView)

            GlobalScope.launch(Dispatchers.IO) {
                val ratings = itemDao.getItemRatings(item.id)
                GlobalScope.launch(Dispatchers.Main) {
                    if (ratings.isNotEmpty()) {
                        val averageRating = ratings.map {
                            it.ratingValue
                        }.average()
                        rating.rating = averageRating.toFloat()
                    } else {
                        Toast.makeText( 
                            itemView.context,
                            "No data",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }

            redeemTextView.setOnClickListener {
                val intent = Intent(itemView.context, ItemRedeemActivity::class.java)
                intent.putExtra("item", item)
                itemView.context.startActivity(intent)
            }

            itemComment.setOnClickListener {
                val intent = Intent(itemView.context, AddRatingActivity::class.java)
                intent.putExtra("itemId", item.id)
                itemView.context.startActivity(intent)
            }

        }
    }


}
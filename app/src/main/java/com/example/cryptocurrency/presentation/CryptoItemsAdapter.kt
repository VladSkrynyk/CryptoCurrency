package com.example.cryptocurrency.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrency.R
import com.example.cryptocurrency.domain.CryptoItem
import com.squareup.picasso.Picasso

class CryptoItemsAdapter() :
        ListAdapter<CryptoItem, CryptoItemsAdapter.ViewHolder>(CryptoItemDiffUtil()){

    private val TAG = "XXXX"

    interface ItemsInteractionListener {
        fun onClick(shopItem: CryptoItem)

    }

    var itemsInteractionListener: ItemsInteractionListener? = null

    class ViewHolder(
        val view: View,
    ) : RecyclerView.ViewHolder(view) {
        val cardView = view.findViewById<CardView>(R.id.cardView_cryptoItem)
        val name = view.findViewById<TextView>(R.id.textViewName)
        val price = view.findViewById<TextView>(R.id.textViewCount)
        //val min = view.findViewById<TextView>(R.id.textViewMin)
        //val max = view.findViewById<TextView>(R.id.textViewMax)
        //val lastMarket = view.findViewById<TextView>(R.id.textViewLastMarket)
        val imageView = view.findViewById<ImageView>(R.id.imageViewCrypto)
    }

    val ITEM_ENADBED = 1
    val ITEM_DISNADBED = 2

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view =
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.shop_item, viewGroup, false)
        return ViewHolder(view)
    }


    // Replace the contents of a view (invoked by the layout manager)
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val shopItem = getItem(position)
        viewHolder.name.text = shopItem.fullName.toString()
        viewHolder.price.text = "Price: " + shopItem.price.toString()
        //viewHolder.min.text = "LowDay: " + shopItem.min.toString()
        //viewHolder.max.text = "HighDay: " + shopItem.max.toString()
        //viewHolder.lastMarket.text = "LastMarket: " + shopItem.lastMarket.toString()

        Picasso.get()
            .load(shopItem.imageUrl)
            .placeholder(R.drawable.img_1) // Плейсхолдер поки завантажується
            .error(R.drawable.img) // Помилка завантаження
            .into(viewHolder.imageView)
        viewHolder.cardView.setOnClickListener {
            itemsInteractionListener?.onClick(shopItem)
        }

    }


}
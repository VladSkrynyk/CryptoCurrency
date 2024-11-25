package com.example.cryptocurrency.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrency.R
import com.example.cryptocurrency.domain.ShopItem

class ShopItemsAdapter() :
    //RecyclerView.Adapter<ShopItemsAdapter.ViewHolder>() {

    ListAdapter<ShopItem, ShopItemsAdapter.ViewHolder>(ShopItemDiffUtil()) {

//    var items: List<ShopItem> = listOf()
//        set(value: List<ShopItem>) {
//            field = value
//
//            notifyDataSetChanged()
//        }


    interface ItemsInteractionListener {
        fun onClick(shopItem: ShopItem)
        fun onLongClick(shopItem: ShopItem) : Boolean
        fun onSwiped(shopItem: ShopItem)
    }

    var itemsInteractionListener : ItemsInteractionListener? = null
//        var clickListener : ( (position: Int) -> Unit )? = null

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(
        val view: View
    ) : RecyclerView.ViewHolder(view) {
        val cardView = view.findViewById<CardView>(R.id.cardView_shopItem)
        val name = view.findViewById<TextView>(R.id.textViewName)
        val count = view.findViewById<TextView>(R.id.textViewCount)
    }

    val ITEM_ENABLED = 1
    val ITEM_DISABLED = 2

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view =
            if (viewType == ITEM_DISABLED) {
                LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.shop_item_disabled, viewGroup, false)
            } else {
                LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.shop_item, viewGroup, false)
            }


        // Create binding which contains view.
//        val binding = CatItemBinding.inflate(
//            LayoutInflater.from(viewGroup.context),
//            viewGroup,
//            false
//        )

        return ViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        val shopItem = getItem(position)
        val type = if (shopItem.isActive) ITEM_ENABLED else ITEM_DISABLED
        return type
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val shopItem = getItem(position)
        viewHolder.name.text = shopItem.name
        viewHolder.count.text = shopItem.count.toString()
        viewHolder.cardView.setOnClickListener {
            itemsInteractionListener?.onClick(shopItem)
        }

        viewHolder.cardView.setOnLongClickListener {
            itemsInteractionListener?.onLongClick(shopItem) ?: false
        }
    }

    private val TAG = "XXXX"

    // Return the size of your dataset (invoked by the layout manager)
    // override fun getItemCount() = items.size

    var simpleItemTouchCallback: ItemTouchHelper.SimpleCallback = object :
        ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
//                    or ItemTouchHelper.DOWN or ItemTouchHelper.UP
        ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder,
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
            //Remove swiped item from list and notify the RecyclerView
            val position = viewHolder.adapterPosition
            Log.d(TAG, "onSwiped: $position")
            // val shopItem = items[position]
            val shopItem = getItem(position)

        //            val shopItem = getItem(position)
            itemsInteractionListener?.onSwiped(shopItem)

            // notifyDataSetChanged()  // FIXME: rebuild entire RecyclerView. Used only for debugging

//            arrayList.remove(position)
//            adapter.notifyDataSetChanged()
        }
    }

}
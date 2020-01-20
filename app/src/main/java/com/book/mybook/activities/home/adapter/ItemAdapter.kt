package com.book.mybook.activities.home.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.book.mybook.R
import com.book.mybook.activities.home.ui.ItemDetailFragment
import com.book.mybook.activities.home.ui.MainActivity
import com.book.mybook.model.Item
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.adapter_item_trending.view.*

class ItemAdapter(private val context: Context, private val items: ArrayList<Item>):
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        var rootLayout: ConstraintLayout = view.rootLayout
        var imageView: ImageView = view.imageView
        var titleView: TextView = view.title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.adapter_item_trending, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        item.volumeInfo.imageLinks?.let {
            Picasso.get().load(it.thumbnail).transform(RoundedCornersTransformation(10, 0)).
                into(holder.imageView)
        }
        holder.titleView.text = item.volumeInfo.title
        holder.rootLayout.setOnClickListener {
            goToItemDetail(item)
        }
    }

    private fun goToItemDetail(item: Item) {
        val fragment = ItemDetailFragment()
        val args = Bundle()
        args.putSerializable(fragment.labelParam, item)
        fragment.arguments = args
        val mainContext = (context as MainActivity)
        mainContext.replaceFragment(fragment)
    }
}
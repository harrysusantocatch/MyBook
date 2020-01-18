package com.book.mybook.activities.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.book.mybook.R
import com.book.mybook.model.Item
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_item.view.*

class ItemGridAdapter(val context: Context, val items: ArrayList<Item>): RecyclerView.Adapter<ItemGridAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        var imageView: ImageView = view.imageView
        var titleView: TextView = view.title
        var authorsView: TextView = view.authors
        var ratingView: RatingBar = view.rating
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.adapter_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        Picasso.get().load(item.volumeInfo.imageLinks.thumbnail).into(holder.imageView)
        holder.titleView.text = item.volumeInfo.title
        item.volumeInfo.authors?.let {
            holder.authorsView.text = getAutors(it)
        }
        holder.ratingView.rating = item.volumeInfo.averageRating
    }

    private fun getAutors(authors: Array<String>): String {
        var result : String = ""
        for (author in authors){
            result = "$result$author, "
        }
        return result.substring(0, result.length-2)
    }
}
package com.book.mybook.activities.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.book.mybook.R
import com.book.mybook.model.Item
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail_item.view.*

class DetailItemFragment : Fragment(), View.OnClickListener {

    val labelParam = "ITEM"
    private var item: Item? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            item = it.getSerializable(labelParam) as Item
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val viewFragment = inflater.inflate(R.layout.fragment_detail_item, container, false)
        setupValue(viewFragment)
        return viewFragment
    }

    private fun setupValue(view: View) {
        item?.let { book ->
            book.volumeInfo.imageLinks?.let {
                val mediumImage = it.thumbnail?.replace("zoom=1", "zoom=3")
                Picasso.get().load(mediumImage).into(view.imageView)
            }
            book.volumeInfo.pageCount?.let {
                view.layoutPages.visibility = View.VISIBLE
                view.page.text = it.toString()
            }
            book.volumeInfo.publishedDate?.let {
                view.layoutPublish.visibility = View.VISIBLE
                view.publishDate.text = it
            }
            view.printType.text = book.volumeInfo.printType
            view.rating.rating = book.volumeInfo.averageRating
            view.title.text = book.volumeInfo.title
            book.volumeInfo.authors?.let {
                view.authors.text = book.volumeInfo.getStringAutors(it)
            }
            view.publisher.text = book.volumeInfo.publisher
            book.volumeInfo.description?.let {
                view.description.text = it
            }
        }
        view.buttonBack.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.buttonBack ->{
                activity?.onBackPressed()
            }
        }
    }
}

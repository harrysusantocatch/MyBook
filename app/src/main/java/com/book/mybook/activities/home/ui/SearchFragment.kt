package com.book.mybook.activities.home.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import com.book.mybook.R
import com.book.mybook.activities.home.adapter.ItemGridAdapter
import com.book.mybook.activities.home.contract.BookContract
import com.book.mybook.activities.home.presenter.BookPresenter
import com.book.mybook.extension.isNetworkConnected
import com.book.mybook.model.Item
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.view.*

class SearchFragment : Fragment(), BookContract.View,
    SearchView.OnQueryTextListener, View.OnClickListener {

    private lateinit var presenter: BookPresenter
    private lateinit var adapter: ItemGridAdapter
    private var items: ArrayList<Item> = arrayListOf()
    private lateinit var lastTextSearch: String
    private val total = 15
    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = BookPresenter(this)
        val myActivity = context as MainActivity
        adapter = ItemGridAdapter(myActivity, items)
        loadFirstData()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val viewFragment = inflater.inflate(R.layout.fragment_search, container, false)
        viewFragment.recycleView.layoutManager = LinearLayoutManager(context)
        viewFragment.recycleView.adapter = adapter
        viewFragment.searchView.setOnQueryTextListener(this)
        viewFragment.buttonRetry.setOnClickListener(this)
        return viewFragment
    }

    private fun loadFirstData() {
        lastTextSearch = "Big Fish"
        presenter.getBooks(lastTextSearch, total, index)
    }

    override fun showMessage(message: String) {
        activity?.runOnUiThread { Toast.makeText(context, message, Toast.LENGTH_LONG).show() }
    }

    override fun viewData(items: ArrayList<Item>) {
        this.items.addAll(items)
        activity?.runOnUiThread { adapter.notifyDataSetChanged() }
    }

    override fun checkConnection() {
        activity?.runOnUiThread {
            context?.let {
                if(!it.isNetworkConnected){
                    cardOffline.visibility = View.VISIBLE
                }else{
                    cardOffline.visibility = View.GONE
                }
            }
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let {
            lastTextSearch = it
            items.clear()
            presenter.getBooks(it, total, index)
        }
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.buttonRetry -> {
                cardOffline.visibility = View.GONE
                presenter.getBooks(lastTextSearch, total, index)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        checkConnection()
    }

    override fun onResume() {
        super.onResume()
        checkConnection()
    }

}

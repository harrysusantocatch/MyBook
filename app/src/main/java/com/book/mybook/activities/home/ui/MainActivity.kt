package com.book.mybook.activities.home.ui

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.book.mybook.R
import com.book.mybook.activities.home.adapter.ItemGridAdapter
import com.book.mybook.activities.home.contract.BookContract
import com.book.mybook.activities.home.presenter.BookPresenter
import com.book.mybook.extension.isNetworkConnected
import com.book.mybook.model.Item
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity(), BookContract.View,
    SearchView.OnQueryTextListener, View.OnClickListener {

    private lateinit var presenter: BookPresenter
    private lateinit var adapter: ItemGridAdapter
    private var items: ArrayList<Item> = arrayListOf()
    private lateinit var lastTextSearch: String
    private val total = 15
    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = BookPresenter(this)
        adapter = ItemGridAdapter(this, items)
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = adapter
        loadFirstData("Mars")
        searchView.setOnQueryTextListener(this)
        buttonRetry.setOnClickListener(this)
    }

    private fun loadFirstData(text: String) {
        lastTextSearch = text
        presenter.getBooks(lastTextSearch, total, index)
    }

    override fun showMessage(message: String) {
        runOnUiThread { Toast.makeText(this, message, Toast.LENGTH_LONG).show() }
    }

    override fun viewData(items: ArrayList<Item>) {
        this.items.addAll(items)
        runOnUiThread { adapter.notifyDataSetChanged() }
    }

    override fun checkConnection() {
        runOnUiThread {
            if(!isNetworkConnected){
                cardOffline.visibility = View.VISIBLE
            }else{
                cardOffline.visibility = View.GONE
            }
        }
    }

    override fun onResume() {
        super.onResume()
        checkConnection()
    }

    override fun onPause() {
        super.onPause()
        checkConnection()
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
}

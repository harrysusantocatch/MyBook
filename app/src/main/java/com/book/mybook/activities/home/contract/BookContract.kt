package com.book.mybook.activities.home.contract

import com.book.mybook.model.Item

interface BookContract {
    interface View{
        fun showMessage(message: String)
        fun viewData(items: ArrayList<Item>)
        fun checkConnection()
    }
    interface Presenter{
        fun getBooks(text: String, total: Int, index: Int)
    }
}
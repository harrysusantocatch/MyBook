package com.book.mybook.activities.home.contract

import com.book.mybook.model.Item

interface TrendingContract {
    interface View{
        fun viewTrendingData(items: ArrayList<Item>)
        fun showMessageErrorTrending(message: String)
    }
    interface Presenter{
        fun getTrendingBooks(text: String, total: Int, index: Int)
    }
}
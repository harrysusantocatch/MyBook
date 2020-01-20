package com.book.mybook.activities.home.contract

import com.book.mybook.model.Item

interface BestSellerContract {
    interface View{
        fun viewBestSellerData(items: ArrayList<Item>)
        fun showMessageErrorBestSeller(message: String)
    }
    interface Presenter{
        fun getBestSellerBooks(text: String, total: Int, index: Int)
    }
}
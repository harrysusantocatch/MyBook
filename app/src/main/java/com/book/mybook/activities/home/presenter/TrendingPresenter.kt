package com.book.mybook.activities.home.presenter

import com.book.mybook.activities.home.contract.TrendingContract
import com.book.mybook.api.BookApiManager

class TrendingPresenter(private val view: TrendingContract.View,
                        private val api: BookApiManager = BookApiManager.instance) : TrendingContract.Presenter{
    override fun getTrendingBooks(text: String, total: Int, index: Int) {
        api.getBooks(text, total, index){
            when(it != null){
                true -> {
                    if(it.size > 0){
                        view.viewTrendingData(it)
                    }else{
                        view.showMessageErrorTrending("Book not found")
                    }
                }
                false ->{
                    view.showMessageErrorTrending("Something's wrong")
                }
            }
        }
    }
}
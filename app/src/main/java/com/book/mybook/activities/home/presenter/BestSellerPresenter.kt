package com.book.mybook.activities.home.presenter

import com.book.mybook.activities.home.contract.BestSellerContract
import com.book.mybook.api.BookApiManager

class BestSellerPresenter(private val view: BestSellerContract.View,
                          private val api: BookApiManager = BookApiManager.instance) : BestSellerContract.Presenter{
    override fun getBestSellerBooks(text: String, total: Int, index: Int) {
        api.getBooks(text, total, index){
            when(it != null){
                true -> {
                    if(it.size > 0){
                        view.viewBestSellerData(it)
                    }else{
                        view.showMessageErrorBestSeller("Book not found")
                    }
                }
                false ->{
                    view.showMessageErrorBestSeller("Something's wrong")
                }
            }
        }
    }
}
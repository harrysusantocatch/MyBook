package com.book.mybook.activities.home.presenter

import com.book.mybook.activities.home.contract.BookContract
import com.book.mybook.api.BookApiManager

class BookPresenter(private val view: BookContract.View,
                    private val api: BookApiManager = BookApiManager.instance) : BookContract.Presenter{
    override fun getBooks(text: String, total: Int, index: Int) {
        api.getBooks(text, total, index){
            when(it != null){
                true -> {
                    if(it.size > 0){
                        view.viewData(it)
                    }else{
                        view.showMessage("Book not found")
                    }
                }
                false ->{
                    view.checkConnection()
                }
            }
        }
    }
}
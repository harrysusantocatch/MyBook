package com.book.mybook.api

import com.book.mybook.model.Item
import io.reactivex.schedulers.Schedulers

class BookApiManager {
    companion object{
        val instance = BookApiManager()
        private val bookApi by lazy { IBookApi.instance.bookmarkUrlRequest() }
    }

    fun getBooks(text: String, total: Int, index: Int, onFinished: (items: ArrayList<Item>?) -> Unit){
        bookApi.getBooks(text, total, index)
            .subscribeOn(Schedulers.io())
            .subscribe({
                if(it.isSuccessful){
                    it.body()?.let {volume ->
                        onFinished(volume.items)
                    }
                }else
                    onFinished(arrayListOf())
            }, {
                onFinished(null)
            })
    }
}
package com.book.mybook.api

import com.book.mybook.model.Volume
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface IBookApi {
    companion object Builder {
        val instance = IBookApi
        private const val BASE_URL = "https://www.googleapis.com/books/v1/"
        fun bookmarkUrlRequest(): IBookApi {
            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(IBookApi::class.java)
        }
    }

    @GET("volumes")
    fun getBooks(@Query("q") text: String,
                 @Query("maxResults") total: Int,
                 @Query("startIndex") index: Int): Observable<Response<Volume>>
}
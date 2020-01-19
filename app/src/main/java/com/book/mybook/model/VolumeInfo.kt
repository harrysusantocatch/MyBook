package com.book.mybook.model

import java.io.Serializable

class VolumeInfo(var title: String,
                 var authors: Array<String>?,
                 var publisher: String?,
                 var publishedDate: String?,
                 var description: String?,
                 var pageCount: Int?,
                 var printType: String,
                 var imageLinks: ImageLink?,
                 var averageRating: Float): Serializable{
    fun getStringAutors(authors: Array<String>): String {
        var result : String = ""
        for (author in authors){
            result = "$result$author, "
        }
        return result.substring(0, result.length-2)
    }
}
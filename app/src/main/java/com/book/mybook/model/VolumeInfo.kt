package com.book.mybook.model

import java.io.Serializable

class VolumeInfo(var title: String,
                 var authors: Array<String>,
                 var publisher: String,
                 var publishedDate: String,
                 var description: String,
                 var pageCount: Int,
                 var imageLinks: ImageLink,
                 var averageRating: Float): Serializable
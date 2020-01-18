package com.book.mybook.model

import java.io.Serializable

class Volume(var kind: String,
             var totalItems: Int,
             var items: ArrayList<Item>): Serializable
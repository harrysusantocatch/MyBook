package com.book.mybook.model

import java.io.Serializable

class Item(var id: String,
           var volumeInfo: VolumeInfo,
           var retailPrice: RetailPrice): Serializable
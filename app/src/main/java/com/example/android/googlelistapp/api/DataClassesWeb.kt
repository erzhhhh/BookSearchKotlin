package com.example.android.googlelistapp.api

import com.google.gson.annotations.SerializedName

data class BooksResult(val items :  List<BookInfo>)

data class VolumeInfo(val title: String, val authors: List<String>)

data class BookInfo(val id : String,
                    @SerializedName("selfLink") val selfLink : String,
                    @SerializedName("volumeInfo") val volumeInfo : VolumeInfo)
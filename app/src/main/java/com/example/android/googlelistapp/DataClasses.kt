package com.example.android.googlelistapp

data class Bookie (val bookId : String, val link : String, val volume : MutableList<BooksInfo>)

data class BooksInfo(val title : String, val subtitle : String, val publishedDate : String) {

    override fun toString(): String {
        return "${title} - ${subtitle} - ${publishedDate}"
    }
}
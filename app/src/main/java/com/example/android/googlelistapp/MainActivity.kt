package com.example.android.googlelistapp

import android.R
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.android.googlelistapp.api.GoogleApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    lateinit var listView : ListView
    lateinit var bookAdapter : ArrayAdapter<String>
    var bookieis = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listView = ListView(this)
        setContentView(listView)
        bookAdapter = ArrayAdapter(this, R.layout.simple_list_item_1, bookieis)
        listView.adapter = bookAdapter

        val api = GoogleApi()
        api.loadBooks()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                bookBook -> bookieis.add("${bookBook.bookId} - ${bookBook.link} \n ${bookBook.link}")
            },{
                e -> e.printStackTrace()
            },{
                bookAdapter.notifyDataSetChanged()
            })
    }
}
package com.example.android.googlelistapp.api

import com.example.android.googlelistapp.Bookie
import com.example.android.googlelistapp.BooksInfo
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class GoogleApi {

    val service: GoogleBookDef
//    val peopleCache = mutableMapOf<String, Person>()

    init {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/books/v1/volumes/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.build())
            .build()
        service = retrofit.create<GoogleBookDef>(GoogleBookDef::class.java)
    }

    fun loadBooks(): Observable<Bookie> {
        return service.listBoooks()
            .flatMap { booksResult -> Observable.fromIterable(booksResult.items) }
            .map { bookInfo ->
                Bookie(bookInfo.id, bookInfo.selfLink, ArrayList<BooksInfo>())
            }
    }
}
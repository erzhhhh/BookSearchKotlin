package com.example.android.googlelistapp.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface GoogleBookDef {
    @GET("?q=flowers")
    fun listBoooks() : Observable<BooksResult>
//    @GET("people/{personId}")
//    fun loadPerson(@Path("personId") personId : String) : Observable<Person?>
}

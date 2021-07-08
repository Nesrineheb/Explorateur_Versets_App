package com.example.quranreader.jsonPlaceHolder



import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface JsonPlaceHolderPageApi {
    @GET("/page/{id}")
    fun getPage(@Path("id") imageName: String?, callback: Callback<Response<*>?>?)
}
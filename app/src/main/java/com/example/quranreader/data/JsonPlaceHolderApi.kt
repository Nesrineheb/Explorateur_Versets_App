package com.example.quranreader.data




import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JsonPlaceHolderApi {
    @GET("/v4/verses/by_key/{verse_key}?fields=page_number")
    fun getAya(@Path("verse_key") verse_key:String?):Call<JsonObject>
    // val ayah: Call<Ayah>
}
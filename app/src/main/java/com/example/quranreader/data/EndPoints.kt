package com.example.quranreader.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EndPoints {



    @GET("by_key/{verse_key}?words=false&fields=page_number")
    fun getDoctor(@Path("verse_key") verse_key:String?, @Query("audio") audio_key:String?): Call<Ayah>

    @GET("by_key/{verse_key}?language=en?words=false")
    fun getTranslation(@Path("verse_key") verse_key:String?,@Query("translations") translations:String?): Call<Ayah>
}
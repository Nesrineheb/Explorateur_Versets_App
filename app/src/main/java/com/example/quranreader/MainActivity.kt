package com.example.quranreader

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.quranreader.jsonPlaceHolder.JsonPlaceHolderPageApi
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val button= findViewById<Button>(R.id.button)


        button.setOnClickListener {
            val intent = Intent(this, OptionsActivity::class.java)

            startActivity(intent)
        }




    }
}
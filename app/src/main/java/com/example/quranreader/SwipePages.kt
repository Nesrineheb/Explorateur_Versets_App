package com.example.quranreader

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso

class SwipePages : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe_pages)

        var myImageView=findViewById<ImageView>(R.id.imageView)
        var buttonLeft=findViewById<FloatingActionButton>(R.id.floatingButtonLeft)
        var buttonRight=findViewById<FloatingActionButton>(R.id.floatingButtonRight)
        var pageText=findViewById<TextView>(R.id.PageText)


        val sharedpreferences = getSharedPreferences("last_page", Context.MODE_PRIVATE)
        var page =sharedpreferences.getInt("last_page", 1)
        var editor: SharedPreferences.Editor = sharedpreferences.edit()
        pageText.text=page.toString()
        if (page === 1){
            buttonRight.setVisibility(View.GONE)
        } else if (page ===604){
            buttonLeft.setVisibility(View.GONE)
        }


        Picasso.get().load("https://quran-images-api.herokuapp.com/show/page/$page").into(myImageView)


        buttonLeft.setOnClickListener {
            page ++
            Picasso.get().load("https://quran-images-api.herokuapp.com/show/page/$page").into(myImageView)
            pageText.text=page.toString()

            editor.putInt("last_page", page)

            editor.commit()

            if (page === 2){
                buttonRight.setVisibility(View.VISIBLE)
                }
           if (page ===604){
                buttonLeft.setVisibility(View.GONE)
            }
        }
        buttonRight.setOnClickListener {
            page --
            Picasso.get().load("https://quran-images-api.herokuapp.com/show/page/$page").into(myImageView)
            pageText.text=page.toString()
            editor.putInt("last_page", page)

            editor.commit()

            if (page===1){
                buttonRight.setVisibility(View.GONE)
            }
            if (page === 603){
                buttonLeft.setVisibility(View.VISIBLE)
            }
        }

    }
}
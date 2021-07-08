package com.example.quranreader

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class OptionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_options)




        val buttonOne= findViewById<Button>(R.id.button2)
        val buttonTwo= findViewById<Button>(R.id.button3)
        buttonOne.setOnClickListener {
            val intent = Intent(this, SwipePages::class.java)

            startActivity(intent)
        }
        buttonTwo.setOnClickListener {
            val intent = Intent(this, ListAyaActivity::class.java)

            startActivity(intent)
        }
    }
}
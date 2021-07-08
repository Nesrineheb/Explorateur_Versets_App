package com.example.quranreader

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quranreader.data.Quran
import com.example.quranreader.data.RoomService
import com.example.quranreader.recyclerListAyatOfRacine.ListAyatRacineAdapter
import com.example.quranreader.recyclerListRacine.ListAyaAdapter

class ListeAyatOfRacine : AppCompatActivity(), View.OnClickListener {

    lateinit var adapter: ListAyatRacineAdapter
    lateinit var ayat: MutableList<Quran>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liste_ayat_of_racine)





        val recyclerView = findViewById(R.id.ayah_recycler_view) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        //val ayas2 = mutableListOf<Ayah>()

        ayat= RoomService.getDataBase(applicationContext).getQuranDAO().getMotsByIdRacine(intent.getIntExtra("idRacine",5)).toMutableList()
        println("DDDDDDDaaam ${ayat}")

        adapter= ListAyatRacineAdapter(ayat, this)
        recyclerView.adapter = adapter

    }
    private fun showAyaDetail(i: String) {
        val intent = Intent(this, AyaDetailActivity::class.java)
        intent.putExtra("idAya",i)

        startActivity(intent)

    }
    override fun onClick(v: View?) {
        if (v != null) {
            if (v.tag != null) {


                showAyaDetail(v.tag as String)


            }
        }
        else{
            println("rani null")
        }
    }
}
package com.example.quranreader.recyclerListRacine

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.quranreader.ListAyaActivity
import com.example.quranreader.ListeAyatOfRacine
import com.example.quranreader.R
import com.example.quranreader.data.Quran

class ListAyaAdapter(val ayas: List<Quran>, val itemClickListener: View.OnClickListener)
    : RecyclerView.Adapter<ListAyaAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView = itemView.findViewById(R.id.card_view) as CardView
        val ayaView = cardView.findViewById(R.id.racine) as TextView



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_racine, parent, false)
        return ViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ayah = ayas[position]
        holder.cardView.setOnClickListener(itemClickListener)
        holder.cardView.tag = ayah.idRacine
        holder.ayaView.text = ayah.texteAR


    }

    override fun getItemCount(): Int {
        return ayas.size
    }
}
package com.example.quranreader.recyclerListAyatOfRacine

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.quranreader.ListeAyatOfRacine
import com.example.quranreader.R
import com.example.quranreader.data.Quran
import com.example.quranreader.recyclerListRacine.ListAyaAdapter

class ListAyatRacineAdapter(val ayas: List<Quran>, val itemClickListener: View.OnClickListener)
    : RecyclerView.Adapter<ListAyaAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView = itemView.findViewById(R.id.card_view) as CardView
        val ayaView = cardView.findViewById(R.id.ayah) as TextView



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAyaAdapter.ViewHolder {
        val viewItem = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_ayah, parent, false)
        return ListAyaAdapter.ViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: ListAyaAdapter.ViewHolder, position: Int) {
        val ayah = ayas[position]
        holder.cardView.setOnClickListener(itemClickListener)
        holder.cardView.tag = ayah.idAya.toString()
        holder.ayaView.text = ayah.texteAya
    }

    override fun getItemCount(): Int {
        return ayas.size
    }


}
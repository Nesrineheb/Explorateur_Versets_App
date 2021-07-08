package com.example.quranreader.data

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader

object LoadData {

    fun getAllDatabase(context: Context):List<Quran>{
        val ayas = mutableListOf<Quran>()
       var it=0
        try {
            val csvfile = InputStreamReader(context.assets.open("qurandata.csv"))
            val csvfilereader = BufferedReader(csvfile)
            var nextLine= csvfilereader.readLine()
            nextLine= csvfilereader.readLine()
            while (nextLine!= null) {
                val data = nextLine.split(',')
                ayas.add(Quran(it,data[0].toInt(),data[1],data[2].toInt(),data[3],data[4].toInt(),data[5].toInt(),data[6],data[7],data[8].toInt(),0,))
                nextLine= csvfilereader.readLine()
                it++
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ayas
    }
}
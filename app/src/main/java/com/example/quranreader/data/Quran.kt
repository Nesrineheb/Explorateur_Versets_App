package com.example.quranreader.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "quran_table")
data class Quran(
        @PrimaryKey
        var id: Int ,
         var idRacine: Int ,
         var texteAR: String? ="" ,
        var idWord: Int,
        var idAya:String? ="",
        var idSourat: Int,
        var numAya :Int,
        var ArabicWord : String? ="",
        var texteAya: String? ="",
        var nbWord: Int,
        var isfav:Int,
        var notefav:String? ="",





    )




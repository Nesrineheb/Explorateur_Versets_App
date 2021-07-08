package com.example.quranreader.data


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Quran::class], version = 1)
abstract class QuranDatabase:RoomDatabase() {



    abstract fun getQuranDAO(): QuranDao





}
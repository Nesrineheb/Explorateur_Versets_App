package com.example.quranreader.data

import android.annotation.SuppressLint
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.concurrent.Executors


@SuppressLint("StaticFieldLeak")
object RoomService {
    lateinit var context: Context
    private var dataBase : QuranDatabase? =null;
    fun getDataBase (context: Context):QuranDatabase{
        if (dataBase==null){ dataBase  = Room.databaseBuilder(context,QuranDatabase::class.java,"quran_database")
            .allowMainThreadQueries()
            .addCallback(object : RoomDatabase.Callback(){
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    Executors.newSingleThreadExecutor().execute{

                        dataBase?.getQuranDAO()?.insertAll(LoadData.getAllDatabase(context))
                    }
                }

            })
            .build()
        }

           return dataBase!!


    }


}
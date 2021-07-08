package com.example.quranreader.data

import android.app.Application

class App : Application() {


        override fun onCreate() {
            super.onCreate()
            RoomService.context = applicationContext
        }

}
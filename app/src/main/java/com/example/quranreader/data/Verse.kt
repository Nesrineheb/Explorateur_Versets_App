package com.example.quranreader.data

class Verse (
    var id:Int,
    var verse_number: Int,
    var verse_key: String="",
    var juz_number: Int,
    var hizb_number:Int,
    var rub_number:Int,
    var sajdah_type: String ="",
    var sajdah_number :Int,
    var page_number :Int,
    var audio:Audio,
    var translations: List<Translations>
        )



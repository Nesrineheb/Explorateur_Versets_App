package com.example.quranreader.data


import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface QuranDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
   fun addMotCoran(motCoran: Quran)

    @Query("Select * from quran_table where idRacine=:idR ")
    fun getMotsByIdRacine(idR:Int):List<Quran>

   @Query("Select  * from quran_table where idAya=:idA ")
   fun getAyaById(idA:String?):Quran

    @Insert
     fun insertAll(Coran: List<Quran>)

    @Query("select * from quran_table" )
    fun getAllQuran():List<Quran>

    @Query("SELECT * FROM quran_table where texteAR in (SELECT DISTINCT texteAR FROM quran_table )")
    fun getAllUniqueRacine():List<Quran>

    @Query("SELECT *  FROM quran_table group by texteAr")
    fun getSUniqueRacine():List<Quran>

    @Update
    fun updatfavAyah(Ayah: Quran)
}
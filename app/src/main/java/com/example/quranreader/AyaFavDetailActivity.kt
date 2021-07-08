package com.example.quranreader

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.widget.AdapterView
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.intervention_plombiers.ConfirmDeleteDialogueFragment
import com.example.quranreader.data.Ayah
import com.example.quranreader.data.Quran
import com.example.quranreader.data.RetrofitService
import com.example.quranreader.data.RoomService
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.time.LocalDate

class AyaFavDetailActivity : AppCompatActivity() {

    companion object {
        val REQUEST_EDIT_Ayah = 1
        val EXTRA_Ayah= "aya"
        val EXTRA_Ayah_INDEX = "ayaid"

        val ACTION_DELETE_TACHE = "com.example.quranreader.actions.ACTION_DELETE_TACHE"
        val ACTION_SAVE_TACHE= "com.example.quranreader.actions.ACTION_SAVE_TACHE"

    }

    lateinit var nomView: TextView
    var ayaid:String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ayah_fav_detail)

        var ayahView = findViewById<TextView>(R.id.ayah)
        nomView = findViewById(R.id.note) as TextView




        var aya: Quran


        var ch=intent.getStringExtra("idAya")

        ayaid=ch

        println("THHHHIS ID AYA §§§§§§ ${ayaid}")


        aya= RoomService.getDataBase(applicationContext).getQuranDAO().getAyaById(ch)
        ayahView.text=aya.texteAya
        nomView.text=aya.notefav





    }




    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_tache_detail, menu)


        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.action_save ->{
                saveTache(ayaid)
                return true  }
            R.id.action_delete ->{
                Log.i("meeeesage debug","delete action")
                showConfirmDeleteDialogue(ayaid)
                return true
            }
            else ->{
                return super.onOptionsItemSelected(item) }
        }

    }

    private fun showConfirmDeleteDialogue(ayaid:String?) {
        val confirmFragment = ConfirmDeleteDialogueFragment()
        confirmFragment.listener = object : ConfirmDeleteDialogueFragment.ConfirmDeleteListener {
            override fun onDialogPositiveClick() {
                deleteTache(ayaid)
            }
            override fun onDialogNegativeClick() {

            } }
        confirmFragment.show(fragmentManager, "delete confirm")
    }

    //Function to display the custom dialog.


    fun deleteTache(ayaid:String?) {
        var aya:Quran
        aya =RoomService.getDataBase(applicationContext).getQuranDAO().getAyaById(ayaid)
        aya.isfav=0
        RoomService.getDataBase(applicationContext).getQuranDAO().deleteAyaByIdFav(aya)

        intent = Intent(ACTION_DELETE_TACHE)
        intent.putExtra(EXTRA_Ayah_INDEX , ayaid)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
    fun saveTache(id:String?) {
        var aya:Quran
        aya= RoomService.getDataBase(applicationContext).getQuranDAO().getAyaById(id)
      aya.notefav= nomView.text.toString()
        //tache.date = dateView.text.toString()
        RoomService.getDataBase(applicationContext).getQuranDAO().updatfavAyah(aya)

        intent = Intent(ACTION_SAVE_TACHE)


        intent.putExtra(EXTRA_Ayah_INDEX, ayaid)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }


}
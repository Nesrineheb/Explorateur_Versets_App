package com.example.quranreader

import android.app.Dialog
import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.widget.AdapterView
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import com.example.quranreader.data.Ayah
import com.example.quranreader.data.Quran
import com.example.quranreader.data.RetrofitService
import com.example.quranreader.data.RoomService
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class AyaDetailActivity : AppCompatActivity() {

    companion object {
        val REQUEST_EDIT_Ayah = 1
        val EXTRA_Ayah= "ayah"
        val EXTRA_Ayah_INDEX = "ayaid"
        val ACTION_FAV_Ayah= "com.example.quranreader.actions.action_favoris"

    }

    private var audio: String? = null
    private lateinit var numPageView :TextView
    private lateinit var translationView :TextView
    var ayaid:String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aya_detail)

        var ayahView = findViewById<TextView>(R.id.ayah)
        var idAyahView = findViewById<TextView>(R.id.id_ayah)
        var numSouratView = findViewById<TextView>(R.id.num_sourat)
        var numAyahView = findViewById<TextView>(R.id.num_ayah)
        var readersSpinner = findViewById<Spinner>(R.id.reader_spinner)
        var nbMotsView = findViewById<TextView>(R.id.nb_mots)


        var fabPlay = findViewById<FloatingActionButton>(R.id.fabPlay)

        var fabStop = findViewById<FloatingActionButton>(R.id.fabStop)
        fabStop.visibility = View.GONE

        var aya:Quran


        var ch=intent.getStringExtra("idAya")

        ayaid=ch

        println("THHHHIS ID AYA §§§§§§ ${ayaid}")


        aya= RoomService.getDataBase(applicationContext).getQuranDAO().getAyaById(ch)
        ayahView.text=aya.texteAya
        idAyahView.text=ch
        numSouratView.text=aya.idSourat.toString()
        numAyahView.text=aya.numAya.toString()
        nbMotsView.text=aya.nbWord.toString()
        getAudioAndPage (ch,"1")
       getTranslation (ch,"149")
        readersSpinner.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                getAudioAndPage (ch,(position+1).toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

        val mediaPlayer = MediaPlayer()
        fabPlay.setOnClickListener {
            try {
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
                mediaPlayer.setDataSource("https://verses.quran.com/"+audio)
                mediaPlayer.prepare()
                mediaPlayer.start()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            fabPlay.visibility = View.GONE
            fabStop.visibility = View.VISIBLE
        }

        fabStop.setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer.reset()
            fabPlay.visibility = View.VISIBLE
            fabStop.visibility = View.GONE
        }
    }
    fun getAudioAndPage (ayaKey:String?,readerKey:String){
        var call =RetrofitService.endpoint.getDoctor(ayaKey,readerKey)
        call!!.enqueue(object : Callback<Ayah> {
            override fun onResponse(call: Call<Ayah>, response: Response<Ayah>) {
                if (response.isSuccessful) {
                    var ayah= response.body()!!

                     numPageView = findViewById<TextView>(R.id.num_page)

                    numPageView.text=ayah.verse.page_number.toString()

                    audio=ayah.verse.audio.url
                    println("TTTTTTTTTTTTTTTTTTTTTTT is the audio $audio")
                }

            }

            override fun onFailure(call: Call<Ayah>, t: Throwable) {

                println(t.message)

            }
        })
    }

    fun getTranslation (ayaKey:String?,translatiobKey:String){
        var call =RetrofitService.endpoint.getTranslation(ayaKey,translatiobKey)
        call!!.enqueue(object : Callback<Ayah> {
            override fun onResponse(call: Call<Ayah>, response: Response<Ayah>) {
                if (response.isSuccessful) {
                    var ayah= response.body()!!

                     translationView = findViewById<TextView>(R.id.translation)

                    translationView.text=ayah.verse.translations.get(0).text

                }

            }

            override fun onFailure(call: Call<Ayah>, t: Throwable) {

                println(t.message)

            }
        })
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_menu_favoris, menu)


        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.action_favoris -> {

                print("the fav string is  ${ayaid}")
                Log.i("meeeesage debug", "favoris action ${ayaid}")

                showDialog(ayaid)

                Log.i("meeeesage debug", "favoris action finnnn ${ayaid}")
                return true
            }
            else ->{ return super.onOptionsItemSelected(item) }
        }

    }

    private fun showDialog(id:String?) {
        var aya:Quran

        print("the fav string is  ${id}")
        aya= RoomService.getDataBase(applicationContext).getQuranDAO().getAyaById(id)

        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.activity_favoris)
        val body = dialog.findViewById(R.id.ajouter_note) as TextView

        val yesBtn = dialog.findViewById(R.id.enregistrer) as Button

        yesBtn.setOnClickListener {
            aya= RoomService.getDataBase(applicationContext).getQuranDAO().getAyaById(id)


            Log.i("meeeesage debug", "this the fav strin id aya ${aya.idAya}")
            aya.notefav=body.text.toString()
            aya.isfav=1
            RoomService.getDataBase(applicationContext).getQuranDAO().updatfavAyah(aya)
            Log.i("meeeesage debug", "this the fav texte note ${aya.notefav}")
            Log.i("meeeesage debug", "this the fav texte note ${aya.isfav.toString()}")

            dialog.dismiss()
        }

        dialog.show()

    }

    //Function to display the custom dialog.


    fun deleteTache() {
        println("hiii am favoris thnks")
    }


}
package com.example.whatapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


class crudtab : AppCompatActivity() {



    private var selectedApps: ArrayList<String>? = null // Change here
    private lateinit var textView: TextView // Declare textView here

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crudtab)

        selectedApps = intent.getStringArrayListExtra("selectedApps") ?: arrayListOf() // Change here

        textView = findViewById(R.id.textView)

        val customButton1 : Button = findViewById(R.id.AddButton)
        val customButton2 : Button = findViewById(R.id.DeleteButton)
        val customButton3 : Button = findViewById(R.id.RandomizeButton)


        customButton1.setBackgroundResource(R.drawable.rounded_button_background)
        customButton1.setTextColor(resources.getColor(R.color.greenfont))
        customButton2.setBackgroundResource(R.drawable.rounded_button_background)
        customButton2.setTextColor(resources.getColor(R.color.greenfont))
        customButton3.setBackgroundResource(R.drawable.rounded_button_background)
        customButton3.setTextColor(resources.getColor(R.color.greenfont))

        customButton1.setOnClickListener {
            val intent = Intent(this@crudtab, addapplicationtab::class.java)
            startActivityForResult(intent, ADD_APP_REQUEST_CODE)
        }

        customButton2.setOnClickListener {
            val intent = Intent(this@crudtab, deleteapplicationtab::class.java)
            startActivityForResult(intent, REMOVE_APP_REQUEST_CODE)
        }


        //RANDOMIZE BUTTON NAGUGULUHAN AKO
        customButton3.setOnClickListener {
            if (selectedApps != null && selectedApps!!.isNotEmpty()) {
                val intent = Intent(this, resulttab::class.java)
                intent.putStringArrayListExtra("selectedApps", selectedApps)
                startActivity(intent)
            } else {
                Toast.makeText(this, "No app selected yet", Toast.LENGTH_SHORT).show()
            }
        }


        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)


    }


    // Handle the received app from addapplicationtab
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_APP_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val appSelected = data?.getStringExtra("selectedApp")
            appSelected?.let {
                selectedApps?.add(it)
                updateAppCount()
            }
        }
        if (requestCode == REMOVE_APP_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val appSelected = data?.getStringExtra("selectedApp")
            appSelected?.let {
                selectedApps?.remove(it)
                updateAppCount()
            }
        }
    }

    // Function to update the TextView with the current number of apps
    private fun updateAppCount() {
        val appCount = selectedApps?.size ?: 0
        textView.text = "$appCount"
    }


//    private fun getRandomApp(): String {
//        val apps = listOf(APP_FACEBOOK, APP_TIKTOK, APP_INSTAGRAM, APP_TWITTER, APP_YOUTUBE, APP_NETFLIX, APP_TWITCH, APP_REDDIT, APP_SHOPEE, APP_SPOTIFY, APP_LAZADA)
//        val randomIndex = (Math.random() * apps.size).toInt()
//        return apps[randomIndex]
//    }

    companion object {
        // Constants representing different app names
        const val APP_FACEBOOK = "facebook"
        const val APP_TIKTOK = "tiktok"
        const val APP_INSTAGRAM = "instagram"
        const val APP_TWITTER = "twitter"
        const val APP_YOUTUBE = "youtube"
        const val APP_NETFLIX = "netflix"
        const val APP_TWITCH = "twitch"
        const val APP_REDDIT = "reddit"
        const val APP_SHOPEE = "shopee"
        const val APP_SPOTIFY = "spotify"
        const val APP_LAZADA = "lazada"

        val ADD_APP_REQUEST_CODE = 1
        val REMOVE_APP_REQUEST_CODE = 2 // Define your request code
    }



}
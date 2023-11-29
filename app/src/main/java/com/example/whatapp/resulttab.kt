package com.example.whatapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class resulttab : AppCompatActivity() {

    lateinit var myTextView : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.result)

        myTextView = findViewById(R.id.applicationName)
        val btnLaunch: Button = findViewById(R.id.btnLaunch)

        val message = intent.getStringExtra("selectedApp")
        if (message != null) {
            myTextView.text = message
            btnLaunch.tag = message // Set selected app as the tag for btnLaunch
        }

        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)



        btnLaunch.setOnClickListener {
            // Retrieve the selected app from the btnLaunch tag
            val selectedApp = btnLaunch.tag as? String

            // If the selectedApp is not null, open the corresponding app or website
            selectedApp?.let { app ->
                when (app) {
                    APP_FACEBOOK -> openApp("fb://page/facebook")
                    APP_TIKTOK -> openApp("https://www.tiktok.com/")
                    APP_INSTAGRAM -> openApp("https://www.instagram.com/")
                    APP_TWITTER -> openApp("https://twitter.com/")
                    APP_YOUTUBE -> openApp("https://www.youtube.com/")
                    APP_NETFLIX -> openApp("https://www.netflix.com/browse")
                    APP_TWITCH -> openApp("https://www.twitch.tv/")
                    APP_REDDIT -> openApp("https://www.reddit.com/")
                    APP_SHOPEE -> openApp("https://shopee.ph")
                    APP_SPOTIFY -> openApp("spotify:artist:")
                    APP_LAZADA -> openApp("https://www.lazada.com/")

                    else -> {
                        // Handle unknown app
                        Toast.makeText(this, "Unknown app", Toast.LENGTH_SHORT).show()
                    }
                }
            } ?: run {
                // Handle case where selectedApp is null
                Toast.makeText(this, "No app selected", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Function to open the selected app or website using an Intent
    private fun openApp(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }


    companion object {
        // Constants representing different app names
        const val APP_FACEBOOK = "Facebook"
        const val APP_TIKTOK = "TikTok"
        const val APP_INSTAGRAM = "Instagram"
        const val APP_TWITTER = "Twitter"
        const val APP_YOUTUBE = "Youtube"
        const val APP_NETFLIX = "Netflix"
        const val APP_TWITCH = "Twitch"
        const val APP_REDDIT = "Reddit"
        const val APP_SHOPEE = "Shopee"
        const val APP_SPOTIFY = "Spotify"
        const val APP_LAZADA = "Lazada"
    }
}
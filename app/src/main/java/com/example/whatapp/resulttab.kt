package com.example.whatapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.app.AlarmManager
import android.app.PendingIntent
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
class resulttab : AppCompatActivity() {

    lateinit var myTextView: TextView
    lateinit var myTextView2: TextView

    // Declare Firebase Database references
    private lateinit var database: FirebaseDatabase
    private lateinit var appDataReference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.result)

        FirebaseApp.initializeApp(this)
        // Initialize Firebase Database
        database = FirebaseDatabase.getInstance()
        appDataReference =
            database.reference.child("WhatAppData") // Replace "appData" with your desired reference name

        myTextView = findViewById(R.id.applicationName)
        myTextView2 = findViewById(R.id.timeName)
        val btnLaunch: Button = findViewById(R.id.btnLaunch)

        val selectedApps = intent.getStringArrayListExtra("selectedApps")
        if (selectedApps != null && selectedApps.isNotEmpty()) {
            val randomIndex = (Math.random() * selectedApps.size).toInt()
            val selectedApp = selectedApps[randomIndex]
            myTextView.text = selectedApp
            btnLaunch.tag = selectedApp // Set selected app as the tag for btnLaunch
        } else {
            myTextView.text = "No apps selected"
            btnLaunch.isEnabled = false // Disable the launch button if no apps selected
        }

        val numbers = arrayOf(10, 20, 30, 40, 50, 60)
        val randomIndex = (Math.random() * numbers.size).toInt()
        val randomElement: Long = numbers[randomIndex].toLong()
        myTextView2.text = randomElement.toString() + " minutes"

        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)



        // Inside your resulttab class, add the following function
         fun updateAppCount(selectedApps: ArrayList<String>?) {

        }

// Call the updateAppCount function where you update the app count in your code
// For example, where you handle the launch button click
        btnLaunch.setOnClickListener {
            val selectedApp = btnLaunch.tag as? String
            val randomElement = myTextView2.text.toString()

            selectedApp?.let { app ->
                // Other code...

                updateAppCount(selectedApps)
            } ?: run {
                // Handle case where selectedApp is null
                Toast.makeText(this, "No app selected", Toast.LENGTH_SHORT).show()
            }
        }






        btnLaunch.setOnClickListener {
            // Retrieve the selected app from the btnLaunch tag
            val selectedApp = btnLaunch.tag as? String
            val randomElement = myTextView2.text.toString()

                //eto yung nagpapacrash tuwing pinipindot yung btnLaunch
                // scheduleNotification(randomElement * 60 * 1000)

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


                //eto yung maangas na part na naglalagay sa database ng info kapag pinush yung btnLaunch
                val appCount = selectedApps?.size ?: 0
                val uniqueKey = appDataReference.push().key
                uniqueKey?.let { key ->
                    val dataMap = HashMap<String, Any>()
                    dataMap["selectedApp"] = selectedApp
                    dataMap["Time"] = randomElement
                    dataMap["appCount"] = appCount

                    // Push data to a new child node under "WhatAppData" with the unique key
                    appDataReference.child(key).setValue(dataMap)
                        .addOnSuccessListener {
                            Toast.makeText(this, "Data pushed to Firebase", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(this, "Failed to push data: ${e.message}", Toast.LENGTH_SHORT).show()
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

    private fun scheduleNotification(delayMillis: Long) {
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val notificationIntent = Intent(this, NotificationReceiver::class.java)

        // Pass the randomElement value to the receiver
        notificationIntent.putExtra("notificationMessage", "Your time is up!")

        val pendingIntent = PendingIntent.getBroadcast(
            this,
            0,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        // Schedule the notification after the specified delay
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + delayMillis, pendingIntent)
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
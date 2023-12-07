package com.example.whatapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class addapplicationtab : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var dataList: ArrayList<DataClass>
    lateinit var imageList: Array<Int>
    lateinit var titleList: Array<String>
    private var selectedApps: ArrayList<String>? = null // Declare at the top of the class


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addapplicationtab)

        selectedApps = intent.getStringArrayListExtra("selectedApps") ?: arrayListOf()


        imageList = arrayOf(
            R.drawable.fbicon,
            R.drawable.tiktokicon,
            R.drawable.igicon,
            R.drawable.twticon,
            R.drawable.yticon,
            R.drawable.netflixicon,
            R.drawable.twitchicon,
            R.drawable.redditicon,
            R.drawable.shopeeicon,
            R.drawable.spotifyicon,
            R.drawable.lazadaicon
        )

        titleList = arrayOf(
            "Facebook",
            "TikTok",
            "Instagram",
            "Twitter",
            "Youtube",
            "Netflix",
            "Twitch",
            "Reddit",
            "Shopee",
            "Spotify",
            "Lazada"
        )

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        dataList = arrayListOf<DataClass>()
        getData()



    }

    private fun getData() {
        for (i in imageList.indices) {
            val dataClass = DataClass(imageList[i], titleList[i])
            dataList.add(dataClass)
        }
        recyclerView.adapter = AppAdapter(dataList, this)
    }

    fun onItemClick(position: Int) {
        val selectedApp = titleList[position]
        if (!selectedApps?.contains(selectedApp)!!) { // Check if the app is already selected
            Toast.makeText(this, "App Added: $selectedApp", Toast.LENGTH_SHORT).show()

            selectedApps?.add(selectedApp) // Add selected app to the ArrayList

            // Notify the intent about the updated ArrayList
            val intent = Intent()
            intent.putExtra("selectedApp", selectedApp)
            setResult(Activity.RESULT_OK, intent)
        } else {
            Toast.makeText(this, "App already added: $selectedApp", Toast.LENGTH_SHORT).show()
        }
        finish()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }



}

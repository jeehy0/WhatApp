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

class deleteapplicationtab : AppCompatActivity() {
    lateinit var recyclerView2: RecyclerView
    lateinit var dataList2: ArrayList<DataClass>
    lateinit var imageList2: Array<Int>
    lateinit var titleList2: Array<String>
    private var selectedApps2: ArrayList<String>? = null // Declare at the top of the class


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deleteapplicationtab)

        selectedApps2 = intent.getStringArrayListExtra("selectedApps") ?: arrayListOf()

        imageList2 = arrayOf(
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

        titleList2 = arrayOf(
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

        recyclerView2 = findViewById(R.id.recyclerView2)
        recyclerView2.layoutManager = LinearLayoutManager(this)
        recyclerView2.setHasFixedSize(true)
        dataList2 = arrayListOf<DataClass>()
        getData()



    }

    private fun getData() {
        for (i in imageList2.indices) {
            val dataClass = DataClass(imageList2[i], titleList2[i])
            dataList2.add(dataClass)
        }
        recyclerView2.adapter = AppAdapter2(dataList2, this)
    }

    fun onItemClick(position: Int) {
        val selectedApp = titleList2[position]
        showToast("App Deleted: $selectedApp")

        selectedApps2?.remove(selectedApp)

        // Notify the intent about the updated ArrayList
        val intent = Intent()
        intent.putExtra("selectedApp", selectedApp)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

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

        val customButton1: Button = findViewById(R.id.continueButton)

        customButton1.setBackgroundResource(R.drawable.rounded_button_background)
        customButton1.setTextColor(resources.getColor(R.color.greenfont))

        customButton1.setOnClickListener {
            val intent = Intent(this@addapplicationtab, crudtab::class.java)
            startActivity(intent)
        }



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
        showToast("App Added: $selectedApp")

        selectedApps?.add(selectedApp) // Add selected app to the ArrayList

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

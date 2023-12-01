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
    lateinit var recyclerView: RecyclerView
    lateinit var titleList: List<String>
    lateinit var drawableList: List<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deleteapplicationtab)

        titleList = intent.getStringArrayListExtra("titleList") ?: emptyList()
        drawableList = intent.getIntegerArrayListExtra("drawableList") ?: emptyList()

        recyclerView = findViewById(R.id.recyclerView2)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        val adapter = AppAdapter2(titleList, drawableList) { position ->
            onItemClick(position)
        }
        recyclerView.adapter = adapter
    }

    fun onItemClick(position: Int) {
        val selectedApp = titleList[position]
        showToast("App Added: $selectedApp")
        val intent = Intent()
        intent.putExtra("selectedApp", selectedApp)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
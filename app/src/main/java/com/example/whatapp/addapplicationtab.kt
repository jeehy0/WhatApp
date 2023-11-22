package com.example.whatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class addapplicationtab : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var dataList : ArrayList<DataClass>
    lateinit var imageList : Array<Int>
    lateinit var titleList : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addapplicationtab)

        val customButton1 : Button = findViewById(R.id.continueButton)

        customButton1.setBackgroundResource(R.drawable.rounded_button_background)
        customButton1.setTextColor(resources.getColor(R.color.greenfont))

        customButton1.setOnClickListener {
            val intent = Intent(this@addapplicationtab, crudtab::class.java)
            startActivity(intent)
        }

        imageList = arrayOf(
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_foreground
        )

        titleList = arrayOf(
            "TitleOne",
            "TitleTwo",
            "TitleThree",
            "TitleFour",
            "TitleFive",
            "TitleSix",
            "TitleSeven",
            "TitleEight",
            "TitleNine",
            "TitleTen"
        )

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        dataList = arrayListOf<DataClass>()
        getData()
    }

    private fun getData() {
        for(i in imageList.indices) {
            var dataClass = DataClass(imageList[i], titleList[i])
            dataList.add(dataClass)
        }
        recyclerView.adapter = AppAdapter(dataList)
    }
}

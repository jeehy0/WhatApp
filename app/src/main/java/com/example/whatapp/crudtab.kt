package com.example.whatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class crudtab : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crudtab)

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
            // Start the SecondActivity
            val intent = Intent(this@crudtab, addapplicationtab::class.java)
            startActivity(intent)
        }

        customButton3.setOnClickListener {
            // Start the SecondActivity
            val intent = Intent(this@crudtab, resulttab::class.java)
            startActivity(intent)
        }

        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    }
}
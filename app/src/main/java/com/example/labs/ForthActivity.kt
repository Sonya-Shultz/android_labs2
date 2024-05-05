package com.example.labs

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ForthActivity  : AppCompatActivity()  {
    private lateinit var button1: Button
    private var isRed: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forth)
        button1 = findViewById(R.id.btn1)
        button1.setBackgroundResource(R.drawable.button_style)
        button1.setOnClickListener {
            isRed = if (isRed) {
                button1.setBackgroundResource(R.drawable.button_style_green)
                false
            }else {
                button1.setBackgroundResource(R.drawable.button_style)
                true
            }
        }

    }
}
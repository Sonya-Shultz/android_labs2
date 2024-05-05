package com.example.labs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var button: Button
    private var valueText: String = "Шульц"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.btn1)
    }

    fun sendText(view: View) {
        val intent = Intent(this, SecActivity::class.java)
        intent.putExtra("Parameter", valueText)
        startActivity(intent)
    }

}
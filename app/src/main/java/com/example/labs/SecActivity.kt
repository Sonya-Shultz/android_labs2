package com.example.labs

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecActivity: AppCompatActivity() {
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sec_activity)

        textView = findViewById(R.id.textView2)
        var textData: String? = intent.getStringExtra("Parameter")
        textView.text = "Параметр: $textData"
    }
}
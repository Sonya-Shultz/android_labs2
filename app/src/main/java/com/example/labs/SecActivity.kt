package com.example.labs

import DatabaseManager
import Groupmate
import android.graphics.Typeface
import android.os.Bundle
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecActivity: AppCompatActivity() {
    private lateinit var gridLayout: GridLayout
    private lateinit var databaseManager: DatabaseManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sec_activity)

        gridLayout = findViewById(R.id.gridLayout)
        databaseManager = DatabaseManager(this)

        val users = databaseManager.getAllGroupmates()
        buildGrid(users)
    }

    private fun buildGrid(users: List<Groupmate>) {
        // Add headers
        val headers = listOf("ID", "Name", "Surname", "Lastname", "Date")
        for (header in headers) {
            val textView = createTextView(header, true)
            gridLayout.addView(textView)
        }

        // Add user data
        for (user in users) {
            val idTextView = createTextView(user.id.toString(), false)
            val nameTextView = createTextView(user.name, false)
            val surnameTextView = createTextView(user.surname, false)
            val lastnameTextView = createTextView(user.lastname, false)
            val dateTextView = createTextView(user.date, false)
            gridLayout.addView(idTextView)
            gridLayout.addView(nameTextView)
            gridLayout.addView(surnameTextView)
            gridLayout.addView(lastnameTextView)
            gridLayout.addView(dateTextView)
        }
    }

    private fun createTextView(text: String, isHeader: Boolean): TextView {
        val textView = TextView(this)
        textView.text = text
        textView.setPadding(16, 16, 16, 16)
        if (isHeader) {
            textView.setTypeface(null, Typeface.BOLD)
        }
        return textView
    }
}
package com.example.labs

import DatabaseManager
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random


private var firstTime: Boolean = true
class MainActivity : AppCompatActivity() {
    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var databaseManager: DatabaseManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById(R.id.btn1)
        button2 = findViewById(R.id.btn2)
        button3 = findViewById(R.id.btn3)

        databaseManager = DatabaseManager(this)
        if (firstTime){
            databaseManager.insertDataAtStart(5)
            firstTime = false
        }

    }

    fun readFromDB(view: View) {
        val intent = Intent(this, SecActivity::class.java)
        startActivity(intent)
    }

    fun addRecord(view: View) {
        val randNumber = Random.nextInt(0, 100)
        databaseManager.insertData("Name$randNumber", "Surname$randNumber", "LastName$randNumber")
        Toast.makeText(this, "Added new record: Name$randNumber Surname$randNumber LastName$randNumber", Toast.LENGTH_SHORT).show()
    }

    fun changeLast(view: View) {
        databaseManager.updateLastRecord("Petro", "Petrenko","Petrovych")
        Toast.makeText(this, "Changed last record!", Toast.LENGTH_SHORT).show()
    }

}
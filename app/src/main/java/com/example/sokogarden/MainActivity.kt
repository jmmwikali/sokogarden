package com.example.sokogarden

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        Find the button by us of their ids
        val Signupbutton = findViewById<Button>(R.id.signupbtn)
        val Signinbutton = findViewById<Button>(R.id.signinbtn)

//        Create the intents to the two activities
        Signupbutton.setOnClickListener {
            val intent = Intent(applicationContext, Signup::class.java)
            startActivity(intent)
        }

//        ==============================================
        Signinbutton.setOnClickListener {
            val intent = Intent(applicationContext, Signin::class.java)
            startActivity(intent)
        }

//        HOW TO RETRIEVE DATA FROM APP STORAGE
//        Find the textview by id
        val usernameText = findViewById<TextView>(R.id.usernameTextView)

//        First access the file where the data is stored
        val prefs = getSharedPreferences("user_session",Context.MODE_PRIVATE)

//        Get the data by use of the key
        val username = prefs.getString("username", "Guest")

//        Update UI
        usernameText.text = "Welcome $username"

//        LOGOUT BUTTON - CLEARS DATA IN APP STORAGE
//        val logoutbutton = findViewById<Button>(R.id.logoutbutton)
//        Setting on click listener
//        logoutbutton.setOnClickListener {
//        Accessing the file that the data was stored in
//            val prefs = getSharedPreferences("user_session",Context.MODE_PRIVATE)
//        Editing the file, clearing the data, saving the changes
//            prefs.edit().clear().apply()
//        Updating the text view
//            usernameText.text = "WELCOME"
//        }


    }
}
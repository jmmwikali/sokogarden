package com.example.sokogarden

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

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
        //        LOGOUT BUTTON - CLEARS DATA IN APP STORAGE
        val logoutbutton = findViewById<Button>(R.id.logoutbutton)
        val welcomeBanner = findViewById<LinearLayout>(R.id.welcomebanner)
        val headerBeforeLogin = findViewById<LinearLayout>(R.id.header1)
        val headerAfterLogin = findViewById<LinearLayout>(R.id.header2)

//        First access the file where the data is stored
        val prefs = getSharedPreferences("user_session",Context.MODE_PRIVATE)

//        Get the data by use of the key
        val username = prefs.getString("username", null)


        if (username != null) {
            Signupbutton.visibility = View.GONE
            Signinbutton.visibility = View.GONE
            welcomeBanner.visibility = View.GONE
            headerBeforeLogin.visibility = View.GONE
            headerAfterLogin.visibility = View.VISIBLE
//        Update UI
            usernameText.text = "Welcome $username"
        }
        else {
            Signupbutton.visibility = View.VISIBLE
            Signinbutton.visibility = View.VISIBLE

        }

//        Setting on click listener
        logoutbutton.setOnClickListener {
//        Accessing the file that the data was stored in
            val prefs = getSharedPreferences("user_session",Context.MODE_PRIVATE)
//        Editing the file, clearing the data, saving the changes
            prefs.edit().clear().apply()
            Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show()

//        Refresh activity
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

//        Find the recycler view and the progress bar by use of their ids
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val progressBar = findViewById<ProgressBar>(R.id.progressbar)

//        Specify the api url endpoint for fetching the products
        val url = "https://jmmwikali.alwaysdata.net/api/get_products"

//        Import the helper class
        val helper = ApiHelper(applicationContext)

//        Inside the helper class, access the function loadProducts
        helper.loadProducts(url, recyclerView, progressBar)

//        Find the about button by use of its id and have the intent
        val aboutButton = findViewById<Button>(R.id.aboutbtn)

//        Below is the intent to the about_activity
        aboutButton.setOnClickListener {
            val intent = Intent(applicationContext, About::class.java)
            startActivity(intent)
        }

    }
}
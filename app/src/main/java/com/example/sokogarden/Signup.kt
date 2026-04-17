package com.example.sokogarden

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.loopj.android.http.RequestParams

class Signup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        Find all the views by use of their ids
        val username = findViewById<EditText>(R.id.username)
        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val phone = findViewById<EditText>(R.id.phone)
        val signUpButton = findViewById<Button>(R.id.signupbtn)
        val signInText = findViewById<TextView>(R.id.signintxt)

//        When the textView is clicked, the user is navigated to the signin page
        signInText.setOnClickListener {
            val intent = Intent(applicationContext, Signin::class.java)
            startActivity(intent)
        }

//        Onclick of the signup button, we want to register a person
        signUpButton.setOnClickListener {
//            Specify the api endpoint
            val api = "https://jmmwikali.alwaysdata.net/api/signup"

//            Create a RequestParams - It is where we are going to hold all the data
            val data = RequestParams()

//            Add/append the username, email, password and the phone on the data
            data.put("username", username.text.toString().trim())
            data.put("email", email.text.toString().trim())
            data.put("password", password.text.toString().trim())
            data.put("phone", phone.text.toString().trim())

//            Import the api helper class
            val helper = ApiHelper(applicationContext)

//           Inside the helper class, access the function; post
            helper.post_signup(api, data)

        }
    }
}
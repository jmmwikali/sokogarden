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

class Signin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signin)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        Find the two edit texts, the button and the textView by use of their ids
        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val signInButton = findViewById<Button>(R.id.signinbtn)
        val signUpTextView = findViewById<TextView>(R.id.signuptxt)

//        On the text view, set on click listener, such that on clicked, it navigates you to the sign up page
        signUpTextView.setOnClickListener {
            val intent = Intent(applicationContext, Signup::class.java)
            startActivity(intent)
        }

//        OnClick of the button sign in, we need to interact with our api endpoint as we pass the two data info: email and password
        signInButton.setOnClickListener {

//            Specify the api endpoint
            val api = "https://jmmwikali.alwaysdata.net/api/signin"

//            Create a request params that will enable you to hold the data in form of a bundle/package
            val data = RequestParams()

//            Add/append/attach the email and the password
            data.put("email", email.text.toString())
            data.put("password", password.text.toString())

//            Import the api helper
            val helper = ApiHelper(applicationContext)

//            By use of the function post_login inside the helper class, post your data
            helper.post_login(api, data)

        }

    }
}
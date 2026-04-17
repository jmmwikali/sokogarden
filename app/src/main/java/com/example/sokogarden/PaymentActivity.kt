package com.example.sokogarden

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.loopj.android.http.RequestParams

class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_payment)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        Find the views by use of their ids
        val txtName = findViewById<TextView>(R.id.txtProductName)
        val txtCost = findViewById<TextView>(R.id.txtProductCost)
        val imgProduct = findViewById<ImageView>(R.id.imgProduct)
        val txtDescription = findViewById<TextView>(R.id.txtDescription)

//        Retrieve the data passed from the previous activity(MainActivity)
        val name = intent.getStringExtra("product_name")
        val cost = intent.getIntExtra("product_cost", 0)
        val description = intent.getStringExtra("product_description")
        val product_photo = intent.getStringExtra("product_photo")


//        Update the TextView with the data passed from the previous activity
        txtName.text = name
        txtCost.text = "Ksh $cost"
        txtDescription.text = description

//        Specify the image url
        val imageUrl = "https://jmmwikali.alwaysdata.net/static/images/$product_photo"

        Glide.with(this)
            .load(imageUrl )
            .placeholder(R.drawable.ic_launcher_background) // Make sure you have a placeholder image
            .into(imgProduct)

//        Find the edit text and the pay now button by use of their ids
        val phone = findViewById<EditText>(R.id.phone)
        val btnPay = findViewById<Button>(R.id.pay)

//        Set Onclick listener on the button
        btnPay.setOnClickListener {
//            Specify the api endpoint for making payment
            val api = "https://jmmwikali.alwaysdata.net/api/mpesa_payment"

//            Create a RequestParams
            val data = RequestParams()

//            Insert data into the request params
            data.put("amount",cost)
            data.put("phone", phone.text.toString().trim())

//            Import the helper class
            val helper = ApiHelper(applicationContext)

//            Access the post function inside the helper class
            helper.post(api, data)

            phone.text.clear()
        }

    }
}
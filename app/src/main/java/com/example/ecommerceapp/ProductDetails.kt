package com.example.ecommerceapp

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.product_details.*

class ProductDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_details)

        val title = intent.getStringExtra("title")
        product_name.text = title

        availability.setOnClickListener {
            AlertDialog.Builder(this)
                .setMessage("It's in stock")
                .setPositiveButton("Ok", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                    }
                })
                .create()
                .show()
        }
    }
}
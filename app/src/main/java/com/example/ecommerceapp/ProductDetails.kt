package com.example.ecommerceapp

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.ecommerceapp.repos.ProductRepository
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.product_details.*

class ProductDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_details)

        val title = intent.getStringExtra("title")

        val product = ProductRepository().getProductByName(title!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                product_name.text = it.title
                Picasso.get().load(it.photoUrl).into(photo)
                thePriceOfProduct.text = "$${it.price}"
            }, {})



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
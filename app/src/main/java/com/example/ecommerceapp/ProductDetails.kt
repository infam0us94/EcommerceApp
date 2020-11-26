package com.example.ecommerceapp

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.ecommerceapp.productdetails.ProductDetailsViewModel
import com.example.ecommerceapp.repos.ProductRepository
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.product_details.*

class ProductDetails : AppCompatActivity() {

    lateinit var viewModel: ProductDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_details)

        viewModel = ViewModelProviders.of(this).get(ProductDetailsViewModel::class.java)

        val title = intent.getStringExtra("title") ?: ""

        viewModel.productDetails.observe(this, Observer {
            product_name.text = it.title
            Picasso.get().load(it.photoUrl).into(photo)
            thePriceOfProduct.text = "$${it.price}"
        })
        viewModel.fetchProductDetails(title)

        addToCartButton.setOnClickListener {

        }

//        val product = ProductRepository().getProductByName(title)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//
//            }, {})

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
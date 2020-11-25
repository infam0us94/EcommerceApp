package com.example.ecommerceapp.repos

import com.example.ecommerceapp.model.Product
import com.google.gson.Gson
import io.reactivex.Single
import java.net.URL

class ProductRepository {
    fun getAllProducts(): Single<List<Product>> {
        return Single.create<List<Product>> {
            val json = URL("https://finepointmobile.com/data/products.json").readText()
            val products = Gson().fromJson(json, Array<Product>::class.java).toList()
            it.onSuccess(products)
        }
    }
}
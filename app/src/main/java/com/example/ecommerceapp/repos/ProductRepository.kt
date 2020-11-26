package com.example.ecommerceapp.repos

import com.example.ecommerceapp.model.Product
import com.google.gson.Gson
import io.reactivex.Single
import java.net.URL

class ProductRepository {
    fun getAllProducts(): Single<List<Product>> {
        return Single.create<List<Product>> {
            it.onSuccess(fetchProducts())
        }
    }

    fun searchForProducts(term: String): Single<List<Product>> {
        return Single.create<List<Product>> {
            val filteredProducts = fetchProducts().filter { it.title.contains(term, true) }
            it.onSuccess(filteredProducts)
        }
    }

    fun getProductByName(name: String): Single<Product> {
       return Single.create<Product> {
           val product = fetchProducts().first {it.title == name}
           it.onSuccess(product)
       }
    }

    fun fetchProducts(): List<Product> {
        val json = URL("https://finepointmobile.com/data/products.json").readText()
        return Gson().fromJson(json, Array<Product>::class.java).toList()
    }
}
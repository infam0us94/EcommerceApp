package com.example.ecommerceapp.productdetails

import com.example.ecommerceapp.model.Product
import retrofit2.http.GET

interface EcommerceApi {

    @GET("data/products.json")
    suspend fun fetchAllProducts(): List<Product>

}
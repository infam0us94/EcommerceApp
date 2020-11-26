package com.example.ecommerceapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ecommerceapp.model.Product

@Dao
interface CartDao {
    @Query("SELECT * FROM CartModel")
    fun getAll(): List<CartModel>

    @Insert
    fun insertAll(vararg products: CartModel)
}
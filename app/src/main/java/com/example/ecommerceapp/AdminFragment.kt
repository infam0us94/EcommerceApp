package com.example.ecommerceapp

import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.ecommerceapp.database.AppDatabase
import com.example.ecommerceapp.database.ProductFromDatabase
import kotlinx.android.synthetic.main.fragment_admin.*
import kotlinx.android.synthetic.main.product_row.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class AdminFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_admin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        submit_button.setOnClickListener {
            val title = product_title.text
            d("Ivan", "button pressed :) $title")

            doAsync {
                val db = activity?.let { it1 ->
                    Room.databaseBuilder(
                        it1.applicationContext,
                        AppDatabase::class.java, "database-name"
                    ).build()
                }

                db?.productDao()?.insertAll(ProductFromDatabase(null, title.toString(), 21.99))

                uiThread {
                    d("Ivan", "home screen")
                }
            }
        }
    }
}
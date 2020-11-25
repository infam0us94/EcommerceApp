package com.example.ecommerceapp

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.ecommerceapp.database.AppDatabase
import com.example.ecommerceapp.database.ProductFromDatabase
import com.example.ecommerceapp.model.Product
import com.example.ecommerceapp.repos.ProductRepository
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import kotlinx.android.synthetic.main.product_row.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL

class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)

        val categories = listOf(
            "Jeans",
            "Socks",
            "Suits",
            "Skirts",
            "Dresses",
            "Hoody",
            "Jeans",
            "Socks",
            "Suits",
            "Skirts",
            "Dresses",
            "Hoody"
        )

        root.categories_recycler_view.apply {
            layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
            adapter = CategoriesAdapter(categories)
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productsRepository = ProductRepository().getAllProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                d("Ivan", "Success ;)")
                recycler_view.apply {
                    layoutManager = GridLayoutManager(activity, 2)
                    adapter = ProductsAdapter(it) { extraTittle, extraImageUrl, photoView ->
                        val intent = Intent(activity, ProductDetails::class.java)
                        intent.putExtra("title", extraTittle)
                        intent.putExtra("photo_url", extraImageUrl)
                        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                            activity as AppCompatActivity,
                            photoView,
                            "photoToAnimate"
                        )
                        startActivity(intent, options.toBundle())
                    }
                }
                progressBar.visibility = View.GONE
            }, {
                d("Ivan", " error :( ${it.message}")
            })
    }
}
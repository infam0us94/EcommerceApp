package com.example.ecommerceapp

import android.os.Bundle
import android.util.Log.d
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.room.Room
import com.example.ecommerceapp.database.AppDatabase
import com.example.ecommerceapp.database.ProductFromDatabase
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    private lateinit var navView: NavigationView
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        doAsync {
            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "database-name"
            ).build()

            db.productDao().insertAll(ProductFromDatabase(null, "Socks - one dozen", 1.99))
            val products = db.productDao().getAll()

            uiThread {
                d("Ivan", "product size ${products.size}")
            }
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, MainFragment())
            .commit()

        navView = findViewById(R.id.nav_view)
        drawerLayout = findViewById(R.id.drawer_layout)

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, MainFragment())
                        .commit()
                }
                R.id.action_jeans -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, JeansFragment())
                        .commit()
                }
                R.id.action_shorts -> {
                    d("Ivan", "shorts was pressed!")
                }
                R.id.action_admin -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, AdminFragment())
                        .commit()
                }
            }
            it.isChecked = true
            drawerLayout.closeDrawers()
            true
        }

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        drawerLayout.openDrawer(GravityCompat.START)
        return true
    }
}
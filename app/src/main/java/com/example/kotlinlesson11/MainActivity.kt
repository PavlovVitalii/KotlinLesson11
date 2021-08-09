package com.example.kotlinlesson11

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var view_pager: ViewPager2
    lateinit var bottom_navigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        view_pager = findViewById(R.id.view_pager)
        bottom_navigation = findViewById(R.id.bottom_navigation)
        view_pager.adapter = MyAdapter(this)

        bottom_navigation.setOnItemSelectedListener {
            return@setOnItemSelectedListener when (it.itemId) {
                R.id.page_1 -> {
                    view_pager.currentItem = 0
                    true
                }
                R.id.page_2 -> {
                    view_pager.currentItem = 1
                    true
                }
                else -> false
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        startActivity(Intent(this, MySettings::class.java))
        return super.onOptionsItemSelected(item)
    }
}

class MyAdapter(activity: MainActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FirstFragment()
            1 -> SecondFragment()
            else -> throw IllegalArgumentException("Only 2 tabs")
        }
    }

}
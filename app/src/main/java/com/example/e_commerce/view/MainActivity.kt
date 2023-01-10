package com.example.e_commerce.view
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.e_commerce.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.besin_recycler_row.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(ProductList())
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    replaceFragment(ProductList())

                    return@setOnItemSelectedListener true
                }
                R.id.basket -> {
                    replaceFragment(BasketFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.category -> {
                    replaceFragment(CategoryFragment())
                    return@setOnItemSelectedListener true
                }
                else -> false
            }
            return@setOnItemSelectedListener true



        }



    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.cikisyap)
        {
            replaceFragment(LoginFragment())
            bottomNavigationView.visibility=View.GONE
        }
        return super.onOptionsItemSelected(item)
    }

    fun replaceFragment(fragment: Fragment) {
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_layout, fragment!!)
            transaction.commit()
        }
    }



}



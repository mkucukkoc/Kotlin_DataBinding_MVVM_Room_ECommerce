package com.example.e_commerce.view
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.example.e_commerce.R
import com.example.e_commerce.model.Product
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.besin_recycler_row.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(ProductList())

    }



    fun replaceFragment(fragment: Fragment) {
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_layout1, fragment!!)
            transaction.commit()
        }
    }
}



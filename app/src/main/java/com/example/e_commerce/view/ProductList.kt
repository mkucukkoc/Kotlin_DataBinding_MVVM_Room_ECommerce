package com.example.e_commerce.view

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.e_commerce.R
import com.example.e_commerce.adapter.ProductRecyclerAdapter
import com.example.e_commerce.viewmodel.ProductListViewModel
import com.google.firebase.auth.FirebaseAuth
import androidx.recyclerview.widget.GridLayoutManager
import com.example.e_commerce.databinding.FragmentProductListBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_product_list.*

class ProductList : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var viewModel: ProductListViewModel
    private var productRecyclerAdapter = ProductRecyclerAdapter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        auth = FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_list, container, false)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.secenekler_menusu,menu)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProductListViewModel::class.java)
        viewModel.refreshData()
        recyclerView.layoutManager = GridLayoutManager(activity?.baseContext, 2)
        recyclerView.adapter = productRecyclerAdapter
        //productları refresh yapmamızı saglayan kod.
        swipeRefreshLayout.setOnRefreshListener {
            recyclerView.visibility=View.GONE
            viewModel.refreshFromInternet()
            swipeRefreshLayout.isRefreshing=false
        }
        observeLiveData()
    }
    fun observeLiveData() {
        viewModel.products.observe(viewLifecycleOwner, Observer { products ->
            products?.let {
                recyclerView.visibility = View.VISIBLE
                productRecyclerAdapter.productListUpdate(products)
            }
        })
    }


}


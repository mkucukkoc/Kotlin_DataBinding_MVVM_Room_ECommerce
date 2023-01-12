package com.example.e_commerce.view

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.e_commerce.R
import com.example.e_commerce.adapter.ProductRecyclerAdapter
import com.example.e_commerce.viewmodel.ProductListViewModel
import com.google.firebase.auth.FirebaseAuth
import androidx.recyclerview.widget.GridLayoutManager
import com.example.e_commerce.model.Product
import kotlinx.android.synthetic.main.fragment_product_list.*
import java.util.*
import kotlin.collections.ArrayList

class ProductList : Fragment() {
    val arrayList=ArrayList<Product>()
    val displayList=ArrayList<Product>()

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
        val menuItem=menu!!.findItem(R.id.search)
        if (menuItem!=null)
        {
            val searchView=menuItem.actionView as SearchView
            searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }
                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText!!.isNotEmpty())
                    {
                        displayList.clear()
                        val search=newText.toLowerCase(Locale.getDefault())
                        productRecyclerAdapter.productList.forEach{
                            if (it.name.toLowerCase(Locale.getDefault()).contains(search))
                            {
                                displayList.add(it)
                            }
                        }
                        recyclerView.adapter!!.notifyDataSetChanged()
                    }
                    else{

                        displayList.clear()
                        displayList.addAll(productRecyclerAdapter.productList)
                        recyclerView.adapter!!.notifyDataSetChanged()
                    }
                    return true
                }
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProductListViewModel::class.java)
        viewModel.refreshData()
        val mydapater=ProductRecyclerAdapter(displayList)

        recyclerView.layoutManager = GridLayoutManager(activity?.baseContext, 2)
        recyclerView.adapter = mydapater

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


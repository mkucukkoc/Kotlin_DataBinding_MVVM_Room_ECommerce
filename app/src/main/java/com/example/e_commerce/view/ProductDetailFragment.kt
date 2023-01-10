package com.example.e_commerce.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.e_commerce.R
import com.example.e_commerce.databinding.BesinRecyclerRowBinding
import com.example.e_commerce.databinding.FragmentProductDetailBinding
import com.example.e_commerce.util.gorselIndir
import com.example.e_commerce.util.placeHolderYap
import com.example.e_commerce.viewmodel.ProductDetailViewModel
import com.example.e_commerce.viewmodel.ProductListViewModel
import kotlinx.android.synthetic.main.besin_recycler_row.*
import kotlinx.android.synthetic.main.besin_recycler_row.productName
import kotlinx.android.synthetic.main.fragment_product_detail.*


class ProductDetailFragment : Fragment() {

    private lateinit var viewModel: ProductDetailViewModel
    private var besinId = 0
    private lateinit var dataBinding: FragmentProductDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_product_detail,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //arguments ler varsa içine girecek.
        arguments?.let {
            besinId = ProductDetailFragmentArgs.fromBundle(it).besinId
        }
        viewModel = ViewModelProvider(this).get(ProductDetailViewModel::class.java)
        viewModel.roomDataGet(1)


        observeLiveData()
    }

    fun observeLiveData() {
        viewModel.productLiveData.observe(viewLifecycleOwner, Observer { product ->
            product?.let {
                dataBinding.productGet=it
               /* productName.text = it.name
                productprice.text = it.price.toString()
                productdescription.text=it.description
                productbrand.text=it.brand
                producttype.text=it.type
                productquantity.text=it.quantityInStock.toString()
                context?.let {
                    productimageView.gorselIndir(product.pictureUrl, placeHolderYap(it))
                }*/
            }
        })
    }
}
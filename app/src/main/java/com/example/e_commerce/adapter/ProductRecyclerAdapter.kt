package com.example.e_commerce.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerce.R
import com.example.e_commerce.databinding.BesinRecyclerRowBinding
import com.example.e_commerce.model.Product
import com.example.e_commerce.view.MainActivity
import com.example.e_commerce.view.ProductList
import com.example.e_commerce.view.ProductListDirections
import kotlinx.android.synthetic.main.besin_recycler_row.view.*


class ProductRecyclerAdapter(var productList: ArrayList<Product>) :
    RecyclerView.Adapter<ProductRecyclerAdapter.ProductViewHolder>(),ProductClickListener {
    class ProductViewHolder(var view: BesinRecyclerRowBinding) : RecyclerView.ViewHolder(view.root)
    {
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        //bu aşagıdaki 3 satırda yapılan şey besin_recycler_row içindeki oluşturdugumuz yapıyı
        //productliste aktardık.
        var inflater = LayoutInflater.from(parent.context)
        //var view = inflater.inflate(R.layout.besin_recycler_row, parent, false)
        val view=DataBindingUtil.inflate<BesinRecyclerRowBinding>(inflater, R.layout.besin_recycler_row,parent,false)
        return ProductViewHolder(view)
    }
    //getItemCount ile veriyi sayıyor.
    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

     holder.view.product=productList[position]
        holder.view.listener=this

        holder.itemView.detailProduct.setOnClickListener {

            val action=ProductListDirections.actionProductListToProductDetailFragment(productList.get(position).uuid)
            Navigation.findNavController(it).navigate(action)
        }
    /* //besin_recycler_row ın içindeki productname i view de göstermek için aşagdaki 1 satır kodu yazdık.
        holder.itemView.productName.text = productList.get(position).name


        holder.itemView.imageView.gorselIndir(productList.get(position).pictureUrl, placeHolderYap(holder.itemView.context))
       */
    }
    //productListUpdate fonksiyonu veriyi kaydetmek ve güncellemek için oluşturuyoruz.
    fun productListUpdate(newProductList: List<Product>) {
        productList.clear()
        productList.addAll(newProductList)
        notifyDataSetChanged()
    }

    override fun clickProduct(view: View)
    {

        val uuid=view.productuuid.text.toString().toIntOrNull()
        uuid?.let {
            val action=ProductListDirections.actionProductListToProductDetailFragment(it)
            Navigation.findNavController(view).navigate(action)


        }
    }
}
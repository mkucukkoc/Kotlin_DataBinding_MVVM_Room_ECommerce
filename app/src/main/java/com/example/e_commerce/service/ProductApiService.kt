package com.example.e_commerce.service

import com.example.e_commerce.model.Product
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ProductApiService {
    //https://raw.githubusercontent.com/mkucukkoc/JsonECommerce/main/products.json
   private val BASE_URL="https://raw.githubusercontent.com/"
    //private val BASE_URL="https://localhost:7287"

    private val api=Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(ProductApi::class.java)

        fun getData():Single<List<Product>>{
            return api.getProduct()
        }
}
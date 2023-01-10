package com.example.e_commerce.service

import com.example.e_commerce.model.Product
import io.reactivex.Single
import retrofit2.http.GET

interface ProductApi
{
@GET("mkucukkoc/JsonECommerce/main/product.json")
//@GET("/api/Products")
fun getProduct():Single<List<Product>>

}
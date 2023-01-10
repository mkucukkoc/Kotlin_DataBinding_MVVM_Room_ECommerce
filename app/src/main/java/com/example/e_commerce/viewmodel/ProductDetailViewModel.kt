package com.example.e_commerce.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.e_commerce.model.Product
import com.example.e_commerce.service.ProductDatabase
import kotlinx.coroutines.launch

class ProductDetailViewModel(application:Application):BaseViewModel(application)
{
    //details butonuna tıklayınca sadece bir tane veriyi getirecegi için list<product> yapmamıza gerek yok.
    var productLiveData=MutableLiveData<Product>()
    fun roomDataGet(uuid: Int)
    {
        launch {
            val dao=ProductDatabase(getApplication()).productDao()
            val product=dao.getProduct(uuid)
            productLiveData.value=product
        }
    }
}
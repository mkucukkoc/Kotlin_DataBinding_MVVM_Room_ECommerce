package com.example.e_commerce.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_commerce.model.Product
import com.example.e_commerce.service.ProductApiService
import com.example.e_commerce.service.ProductDatabase
import com.example.e_commerce.util.PrivateSharedPrefences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

//ViewModel kullanmak için ViewModel() import ediyoruz.
class ProductListViewModel(application: Application) : BaseViewModel(application) {
    var products=MutableLiveData<List<Product>>()
    var errormessage=MutableLiveData<Boolean>()
    var besinYukleniyor=MutableLiveData<Boolean>()
    private var productApiService=ProductApiService()
    private var disposable=CompositeDisposable()
    private val ozelSharedPrefences=PrivateSharedPrefences()
    private var guncellemeZamani=10*60*1000*1000*1000L


     private fun getProducts()
     {
         disposable.add(
             productApiService.getData()
                 .subscribeOn(Schedulers.newThread())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribeWith(object :DisposableSingleObserver<List<Product>>(){
                     override fun onSuccess(t: List<Product>) {
                        sqliteSakla(t)
                         Toast.makeText(getApplication(),"Besinleri Internet den alıyoruz",Toast.LENGTH_LONG).show()

                     }

                     override fun onError(e: Throwable) {
                         errormessage.value=true
                         besinYukleniyor.value=false
                         e.printStackTrace()
                     }
                 })

         )
     }
    fun refreshFromInternet()
    {
        getProducts()
    }
    fun refreshData()
    {
        val kaydedilmeZamani=ozelSharedPrefences.zamaniAl()
        if (kaydedilmeZamani!=null&&kaydedilmeZamani!=0L && System.nanoTime()-kaydedilmeZamani< guncellemeZamani)
        {

        }
        else
        {
           getProducts()
        }
    }
    private fun verilerSqlLiteanAl()
    {
        launch {
            val productLsit=ProductDatabase(getApplication()).productDao().getAllProduct()
            productGetir(productLsit)
            Toast.makeText(getApplication(),"Besinleri Room dan alıyoruz",Toast.LENGTH_LONG).show()
        }
    }
    private fun productGetir(productslist:List<Product>){
        products.value=productslist
        errormessage.value=false
        besinYukleniyor.value=false
    }
    private fun sqliteSakla(productlist: List<Product>)
    {
        launch {
            val dao=ProductDatabase(getApplication()).productDao()
            dao.deleteAllProduct()
            val uuidListesi=dao.insertAll(*productlist.toTypedArray())
            var i=0
            while (i<productlist.size)
            {
                productlist[i].uuid=uuidListesi[i].toInt()
                i=i+1
            }
            productGetir(productlist)
        }
        ozelSharedPrefences.zamaniKaydet(System.nanoTime())
    }
}
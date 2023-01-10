package com.example.e_commerce.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.e_commerce.model.Product

//Data Access Object Nedir?
//Veritabanımıza veri eklem ,silme,güncelleme gibi işlemlerinin sorgularını yazdığımız sınıflardır.
@Dao
interface ProductDAO
{
    //Fonksiyonlar neden suspend tanımlanıyor?
    //suspend ->coroutine scope dan gelmektedir.Yani asenkron programalamdır.eşzamanlılık tasarım desenidir.
    // Asenkron olarak kodun çalışmasını sağlamak için Kotlin diline 1.3 versiyonu ile birlikte girmiştir.
    // Uzun süren işlemlerde ana thread'i bloklamamak için kullanılır.


    //veri tabanına ekleme yapmak için direkt @Insert yazıp aşagısına fonksiyonu yazabiliriz.
    //List<Long> ise long id'ler için yazdık.
    //vararg nedir?vararg 1 den fazla veri ekleme silme gibi işlemlerde kullanılır.
@Insert
suspend fun insertAll(vararg product:Product):List<Long>

@Query("Select * from product")
suspend fun getAllProduct():List<Product>

@Query("Select * from product Where uuid=:productId")
suspend fun getProduct(productId:Int):Product

@Query("Delete from product")
suspend fun deleteAllProduct()

}
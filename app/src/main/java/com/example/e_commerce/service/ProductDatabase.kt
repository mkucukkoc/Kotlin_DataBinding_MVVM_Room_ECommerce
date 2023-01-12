package com.example.e_commerce.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.e_commerce.model.Product

//@Database içine entityleri array içinde istiyor eger başka entityler olsaydı onu da ekleyecktik.
@Database(entities = arrayOf(Product::class), version = 1)
abstract class ProductDatabase : RoomDatabase() {
//abstract nedir?
    //Nesne yönelimli programlamada Soyutlama (Abstraction) ilkesi, eğer bir sınıf için nesne üretmek mantıksız geliyorsa
    // o sınıf soyutlanabilir. Alt sınıfların ortak özelliklerini ve işlevlerini taşıyan ancak henüz bir nesnesi olmayan bir üst
    // sınıf oluşturmak istenirse bir soyut (abstract) üst sınıf oluşturulur.

    abstract fun productDao(): ProductDAO

    companion object {
        @Volatile
        private var instance: ProductDatabase? = null

        private val lock = Any()

        //invoke nedir?Invoke metodu zamana bağlı olarak fonksiyon çalıştırmaya yarar.
        // Coroutine metodu kodun belli bir süre bekledikten sonra tekrar çalışmaya devam etmesine yarar.
        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: createasDatabase(context).also {
                instance = it
            }
        }

        private fun createasDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ProductDatabase::class.java, "productdatabase"
        ).build()
    }
}
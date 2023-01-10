package com.example.e_commerce.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class PrivateSharedPrefences {
    private val ZAMAN="zaman"
    private var sharedPrefences:SharedPreferences?=null
    //volatile nedir?
    //geliştirdiğimiz bir programda, farklı işlem (process) veya donanımsal etkiler sonucunda değeri değişen
    // bir değişken (variable) kullanıyorsak, bu değişkeni volatile olarak tanımlamamız gerekir. Aksi halde derleyici (compiler) bu değişkeni optimize
    // ederek görevini yerine getiremez bir hale sokabilir
    @Volatile private var instance:PrivateSharedPrefences?=null
    private val lock=Any()
    operator fun invoke(context: Context):PrivateSharedPrefences=instance?: synchronized(lock){
        instance?:privateSharedPrefencesDo(context).also{
            instance=it
        }
    }
    private fun privateSharedPrefencesDo(context: Context):PrivateSharedPrefences{
        sharedPrefences=PreferenceManager.getDefaultSharedPreferences(context)
        return PrivateSharedPrefences()
    }
    fun zamaniKaydet(zaman:Long)
    {
        sharedPrefences?.edit(commit=true){
            putLong(ZAMAN,zaman)
        }

    }
    fun zamaniAl()=sharedPrefences?.getLong(ZAMAN,0)
}
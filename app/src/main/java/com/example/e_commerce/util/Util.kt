package com.example.e_commerce.util

import android.content.Context
import android.widget.ImageView
import androidx.constraintlayout.widget.Placeholder
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.e_commerce.R

fun ImageView.gorselIndir(url:String?,placeholder: CircularProgressDrawable)
{
    val options=RequestOptions().placeholder(placeholder)
    Glide.with(context).setDefaultRequestOptions(options).load(url).into(this)
}
fun placeHolderYap(context: Context):CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {

        strokeWidth=8f
        centerRadius=40f
        start()
    }
}
@BindingAdapter("android:downloadImage")
fun dowloadImage(view: ImageView,url:String?)
{
    view.gorselIndir(url, placeHolderYap(view.context))
}

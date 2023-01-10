package com.example.e_commerce.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Product
    (
    @ColumnInfo(name = "name")
    @SerializedName("name")
    var name:String,
    @ColumnInfo(name = "description")
    @SerializedName("description")
    var description:String,
    @ColumnInfo(name = "price")
    @SerializedName("price")
    var price:Long,
    @ColumnInfo(name = "pictureUrl")
    @SerializedName("pictureUrl")
    var pictureUrl:String,
    @ColumnInfo(name = "type")
    @SerializedName("type")
    var type:String,
    @ColumnInfo(name = "brand")
    @SerializedName("brand")
    var brand:String,
    @ColumnInfo(name = "quantityInStock")
    @SerializedName("quantityInStock")
    var quantityInStock:Int

    )
{
    @PrimaryKey(autoGenerate = true)
    var uuid:Int=0
    }

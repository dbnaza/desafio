package com.dbnaza.desafioenjoei.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    @SerializedName("comments")
    val comments: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("discount")
    val discount: Int,
    @SerializedName("finalPrice")
    val finalPrice: Double,
    @SerializedName("images")
    val images: List<String>,
    @SerializedName("isLiked")
    val isLiked: Boolean,
    @SerializedName("likes")
    val likes: Int,
    @SerializedName("maxInstallments")
    val maxInstallments: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("owner")
    val owner: Owner?,
    @SerializedName("price")
    val price: Double
) : Parcelable

@Parcelize
data class Owner(
    @SerializedName("name")
    val name: String,
    @SerializedName("picture")
    val picture: String
) : Parcelable
package com.proy.kmanager1

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("id")  var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("age")
    var age: Int,
    @SerializedName("gender")
    var gender: Int,
    @SerializedName("height")
    var height: Int,
    @SerializedName("weight")
    var weight: Int,
    @SerializedName("smoke")
    var smoke: Int
)
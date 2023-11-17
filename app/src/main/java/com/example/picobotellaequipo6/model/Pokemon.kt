package com.example.picobotellaequipo6.model

import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("name")
    val name:String,
    @SerializedName("img")
    val img:String
)

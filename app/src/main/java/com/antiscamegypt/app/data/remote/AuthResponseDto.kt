package com.antiscamegypt.app.data.remote

import com.google.gson.annotations.SerializedName

data class AuthResponseDto(
    @SerializedName("success")
    val success: Boolean,
    
    @SerializedName("message")
    val message: String,
    
    @SerializedName("token")
    val token: String?
)


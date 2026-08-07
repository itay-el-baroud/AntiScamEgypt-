package com.antiscamegypt.app.data.remote

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApiService {
    @FormUrlEncoded
    @POST("login.php")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): AuthResponseDto

    @FormUrlEncoded
    @POST("register.php")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): AuthResponseDto

    @FormUrlEncoded
    @POST("forgot_password.php")
    suspend fun forgotPassword(
        @Field("email") email: String
    ): AuthResponseDto

    @FormUrlEncoded
    @POST("verification.php")
    suspend fun verifyOtp(
        @Field("email") email: String,
        @Field("otp") otp: String
    ): AuthResponseDto

    @FormUrlEncoded
    @POST("send_email.php")
    suspend fun sendOtp(
        @Field("email") email: String
    ): AuthResponseDto
}

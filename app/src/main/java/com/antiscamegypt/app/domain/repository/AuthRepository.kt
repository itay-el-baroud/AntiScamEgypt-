package com.antiscamegypt.app.domain.repository

import com.antiscamegypt.app.domain.model.AuthResult

interface AuthRepository {
    suspend fun login(email: String, password: String): AuthResult
    suspend fun register(name: String, email: String, password: String): AuthResult
    suspend fun forgotPassword(email: String): AuthResult
    suspend fun verifyOtp(email: String, otp: String): AuthResult
    suspend fun sendOtp(email: String): AuthResult
    fun saveToken(token: String)
    fun getToken(): String?
    fun clearToken()
}

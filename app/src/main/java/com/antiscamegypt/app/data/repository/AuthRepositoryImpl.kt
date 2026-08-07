package com.antiscamegypt.app.data.repository

import com.antiscamegypt.app.data.local.TokenManager
import com.antiscamegypt.app.data.remote.AuthApiService
import com.antiscamegypt.app.domain.model.AuthResult
import com.antiscamegypt.app.domain.repository.AuthRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apiService: AuthApiService,
    private val tokenManager: TokenManager
) : AuthRepository {
    
    override suspend fun login(email: String, password: String): AuthResult {
        return try {
            val response = apiService.login(email, password)
            if (response.success && response.token != null) {
                tokenManager.saveToken(response.token)
                AuthResult.Success(response.token, response.message)
            } else {
                AuthResult.Error(response.message)
            }
        } catch (e: Exception) {
            AuthResult.Error("فشل الاتصال بالسيرفر: ${e.message}")
        }
    }
    
    override suspend fun register(name: String, email: String, password: String): AuthResult {
        return try {
            val response = apiService.register(name, email, password)
            if (response.success && response.token != null) {
                tokenManager.saveToken(response.token)
                AuthResult.Success(response.token, response.message)
            } else {
                AuthResult.Error(response.message)
            }
        } catch (e: Exception) {
            AuthResult.Error("فشل الاتصال بالسيرفر: ${e.message}")
        }
    }
    
    override suspend fun forgotPassword(email: String): AuthResult {
        return try {
            val response = apiService.forgotPassword(email)
            if (response.success) {
                AuthResult.Success("", response.message)
            } else {
                AuthResult.Error(response.message)
            }
        } catch (e: Exception) {
            AuthResult.Error("فشل الاتصال بالسيرفر: ${e.message}")
        }
    }
    
    override suspend fun verifyOtp(email: String, otp: String): AuthResult {
        return try {
            val response = apiService.verifyOtp(email, otp)
            if (response.success) {
                AuthResult.Success("", response.message)
            } else {
                AuthResult.Error(response.message)
            }
        } catch (e: Exception) {
            AuthResult.Error("فشل الاتصال بالسيرفر: ${e.message}")
        }
    }
    
    override suspend fun sendOtp(email: String): AuthResult {
        return try {
            val response = apiService.sendOtp(email)
            if (response.success) {
                AuthResult.Success("", response.message)
            } else {
                AuthResult.Error(response.message)
            }
        } catch (e: Exception) {
            AuthResult.Error("فشل الاتصال بالسيرفر: ${e.message}")
        }
    }
    
    override fun saveToken(token: String) {
        // Already saved in login/register
    }
    
    override suspend fun getToken(): String? {
        return tokenManager.token.first()
    }
    
    override fun clearToken() {
        // Will be implemented with coroutines
    }
}

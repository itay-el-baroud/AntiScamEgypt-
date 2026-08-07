package com.antiscamegypt.app.domain.model

sealed class AuthResult {
    data class Success(val token: String, val message: String) : AuthResult()
    data class Error(val message: String) : AuthResult()
}

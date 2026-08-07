package com.antiscamegypt.app.domain.usecase

import com.antiscamegypt.app.domain.model.AuthResult
import com.antiscamegypt.app.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(name: String, email: String, password: String): AuthResult {
        return repository.register(name, email, password)
    }
}

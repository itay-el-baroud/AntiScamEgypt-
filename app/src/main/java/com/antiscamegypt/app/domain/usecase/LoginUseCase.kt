package com.antiscamegypt.app.domain.usecase

import com.antiscamegypt.app.domain.model.AuthResult
import com.antiscamegypt.app.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): AuthResult {
        return repository.login(email, password)
    }
}

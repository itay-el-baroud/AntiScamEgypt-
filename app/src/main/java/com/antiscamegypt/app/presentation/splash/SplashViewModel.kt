package com.antiscamegypt.app.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antiscamegypt.app.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _isLoggedIn = MutableStateFlow<Boolean?>(null)
    val isLoggedIn: StateFlow<Boolean?> = _isLoggedIn

    fun checkLoginStatus() {
        viewModelScope.launch {
            val token = authRepository.getToken()
            _isLoggedIn.value = !token.isNullOrEmpty()
        }
    }
}

package com.example.shilpakalashowcase

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class UserProfile(
    val uid: String = "",
    val name: String = "",
    val bio: String = "",
    val phone: String = "",
    val role: UserRole = UserRole.USER
)

data class OrderHistory(
    val artworkTitle: String = "",
    val artistName: String = "",
    val timestamp: String = "",
    val price: Double = 0.0,
    val status: String = ""
)

data class AppSettings(
    val notificationsEnabled: Boolean = true,
    val language: String = "English"
)

enum class UserRole {
    USER,
    ARTIST
}

class MainViewModel : ViewModel() {

    private val _currentUser = MutableStateFlow(UserProfile())
    val currentUser: StateFlow<UserProfile> = _currentUser

    private val _orderHistory = MutableStateFlow(
        listOf(
            OrderHistory(
                artworkTitle = "Shilpa Art",
                artistName = "Rakshitha",
                timestamp = "Today",
                price = 500.0,
                status = "Completed"
            )
        )
    )
    val orderHistory: StateFlow<List<OrderHistory>> = _orderHistory

    private val _settings = MutableStateFlow(AppSettings())
    val settings: StateFlow<AppSettings> = _settings

    // LOGIN
    fun login(email: String) {
        _currentUser.value = UserProfile(
            uid = "1",
            name = email,
            role = UserRole.USER
        )
    }

    // LOGOUT
    fun logout() {
        _currentUser.value = UserProfile()
    }

    // SWITCH ACCOUNT ROLE
    fun switchAccount(role: UserRole) {
        _currentUser.value = _currentUser.value.copy(role = role)
    }

    fun updateProfile(profile: UserProfile) {
        _currentUser.value = profile
    }

    fun updateSettings(settings: AppSettings) {
        _settings.value = settings
    }
}
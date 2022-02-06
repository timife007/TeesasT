package com.timife.teesast.auth.domain

import com.timife.teesast.auth.data.AuthResponse
import com.timife.teesast.utils.Result

interface AuthRepository {
    suspend fun registerUser(): Result<AuthResponse>
}
package com.timife.teesast.auth.data

import com.timife.teesast.utils.Result

interface AuthDataSource {
    suspend fun registerUser():Result<AuthResponse>
}
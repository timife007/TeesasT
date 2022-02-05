package com.timife.teesast.auth.data

import com.timife.teesast.utils.Result
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(

) : AuthDataSource {
    override suspend fun registerUser(): Result<AuthResponse> {
        TODO("Not yet implemented")
    }
}
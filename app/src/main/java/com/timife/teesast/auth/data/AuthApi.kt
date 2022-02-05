package com.timife.teesast.auth.data

import com.timife.teesast.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {
    @GET(Constants.AUTH_ENDPOINT)
    suspend fun register() : Response<AuthResponse>
}
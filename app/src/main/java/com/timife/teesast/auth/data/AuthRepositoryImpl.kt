package com.timife.teesast.auth.data

import com.timife.teesast.auth.domain.AuthRepository
import com.timife.teesast.common.di.dispatchers.IoDispatcher
import com.timife.teesast.utils.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val apiService: AuthApi
) : AuthRepository {
    override suspend fun registerUser(): Result<AuthResponse> =
        withContext(ioDispatcher) {
            return@withContext try {
                val result = apiService.register()
                if (result.isSuccessful){
                    val auth = result.body()
                    Result.Success(auth)
                }else{
                    Result.Success(null)
                }
            }catch (exception:Exception){
                Result.Error(exception)
            }
        }
}
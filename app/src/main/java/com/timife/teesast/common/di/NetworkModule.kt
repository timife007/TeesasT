package com.timife.teesast.common.di

import com.timife.teesast.auth.data.AuthApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideApi(retrofit: Retrofit): AuthApi{
        return retrofit.create(AuthApi::class.java)
    }
}
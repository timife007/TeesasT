package com.timife.teesast.common.di

import com.timife.teesast.auth.data.AuthApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit.Builder): AuthApi{
        return retrofit.build().create(AuthApi::class.java)
    }
}
package com.example.paging3.di

import com.example.myapplication.BuildConfig
import com.example.myapplication.Services.ApiService
import com.example.myapplication.Services.ApiService.Companion.BASE_URL
import com.example.myapplication.Services.ApiService.Companion.TMDB_API_KEY
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    val interceptor = Interceptor{
            chain -> val request= chain.request()
        val url = request.url().newBuilder().addQueryParameter("api_key", ApiService.TMDB_API_KEY ).build()
        val newreq= request.newBuilder().url(url).build()
        chain.proceed(newreq)
    }
    val httpClient= OkHttpClient().newBuilder().addInterceptor(interceptor).build()
    val gson= GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
    @Provides
    @Singleton
    fun providesApiService() :ApiService{
        return Retrofit.Builder().baseUrl(BASE_URL).client(httpClient).addConverterFactory(
            GsonConverterFactory.create(gson)
        ).build().create(ApiService::class.java)


    }

}
package com.sujit.openinapp.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.sujit.openinapp.BuildConfig
import com.sujit.openinapp.data.network.ApiService
import com.sujit.openinapp.data.network.TokenInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun providesMoshi(): Moshi =
        Moshi
            .Builder()
            .run {
                add(KotlinJsonAdapterFactory())
                build()
            }

    @Provides
    @Singleton
    fun providesApiService(
        moshi: Moshi,
        client: OkHttpClient,
    ): ApiService = retrofit {
        baseUrl(BuildConfig.BASE_URL)
        addConverterFactory(MoshiConverterFactory.create(moshi))
        client(client)
    }.create(ApiService::class.java)


    @Provides
    @Singleton
    fun providesOkHttp(tokenInterceptor: TokenInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(tokenInterceptor)
            .also { client ->
                client.addInterceptor(HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor.Level.BODY
                    } else {
                        HttpLoggingInterceptor.Level.NONE
                    }
                })
            }
            .build()
    }

    private inline fun retrofit(init: Retrofit.Builder.() -> Unit): Retrofit {
        val retrofit = Retrofit.Builder()
        retrofit.init()
        return retrofit.build()
    }


}
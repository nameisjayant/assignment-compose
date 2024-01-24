package com.sujit.openinapp.data.network

import com.sujit.openinapp.preference.PreferenceStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
    private val preferenceStore: PreferenceStore
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val token = runBlocking { preferenceStore.getPref(PreferenceStore.token).first() }
        val builder = request.newBuilder().header(
            "AUTHORIZATION",
            "Bearer $token"
        )
        return chain.proceed(builder.build())
    }
}
package com.sujit.openinapp.data.network

import com.sujit.openinapp.data.model.ApiModelResponse
import com.sujit.openinapp.data.utils.Constant
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(Constant.END_POINT)
    suspend fun getApiResponse(): Response<ApiModelResponse>
}
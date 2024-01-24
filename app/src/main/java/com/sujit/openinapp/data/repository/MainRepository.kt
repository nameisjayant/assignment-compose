package com.sujit.openinapp.data.repository

import com.sujit.openinapp.data.model.ApiModelResponse
import com.sujit.openinapp.data.network.ApiService
import com.sujit.openinapp.data.utils.ApiState
import com.sujit.openinapp.data.utils.toResultFlow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService
) {

    fun getApiResponse():Flow<ApiState<ApiModelResponse>> = toResultFlow {
        apiService.getApiResponse()
    }

}
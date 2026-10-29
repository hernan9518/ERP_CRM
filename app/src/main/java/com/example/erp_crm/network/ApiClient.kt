package com.example.erp_crm.network

import com.example.erp_crm.model.ClienteCrmDto
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CrmService {
    @GET("users")
    suspend fun obtenerClientes(): List<ClienteCrmDto>
}

object ApiClient {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    val crmService: CrmService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CrmService::class.java)
    }
}
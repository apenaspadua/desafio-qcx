package com.example.desafioqconcursos.network.service

import com.example.desafioqconcursos.network.bean.QuotesResponse
import retrofit2.Call
import retrofit2.http.GET

interface AppAPI {

    @GET("/quotes")
    fun getQuotes(): Call<List<QuotesResponse>>
}
package com.example.desafioqconcursos.network.service

import com.example.desafioqconcursos.network.bean.QuotesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AppAPI {

    @GET("/quotes/page/{page}")
    fun getQuotes(@Path("page") page: Int): Call<List<QuotesResponse>>
}
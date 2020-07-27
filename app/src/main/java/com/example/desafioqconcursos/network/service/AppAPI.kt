package com.example.desafioqconcursos.network.service

import com.example.desafioqconcursos.network.bean.QuotesResponse
import com.example.desafioqconcursos.network.bean.SendVoteRequest
import com.example.desafioqconcursos.network.bean.SendVoteResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AppAPI {

    @GET("/quotes/page/{page}")
    fun getQuotes(@Path("page") page: Int): Call<List<QuotesResponse>>

    @POST("quotes/vote")
    fun sendVote(@Body sendVoteRequest: SendVoteRequest) : Call<SendVoteResponse>
}
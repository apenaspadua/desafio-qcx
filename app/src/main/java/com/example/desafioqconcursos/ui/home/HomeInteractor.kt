package com.example.desafioqconcursos.ui.home

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.desafioqconcursos.network.bean.QuotesResponse
import com.example.desafioqconcursos.network.service.AppAPI
import com.example.desafioqconcursos.network.service.Service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeInteractor(val output: HomeActivity): HomeContract.Interactor {

    private val api = Service.getRetrofitInstance().create(AppAPI::class.java)

    override fun getQuotes() {
        val call: Call<List<QuotesResponse>> = api.getQuotes()
        call.enqueue(object : Callback<List<QuotesResponse>> {
            @RequiresApi(Build.VERSION_CODES.M)
            override fun onFailure(call: Call<List<QuotesResponse>>, t: Throwable) {
                output.onError(t.localizedMessage)
                Log.d("error", t.localizedMessage)
            }

            @RequiresApi(Build.VERSION_CODES.M)
            override fun onResponse(call: Call<List<QuotesResponse>>, response: Response<List<QuotesResponse>>) {
                val quotesResponse = response.body()

                if (quotesResponse != null)
                    output.createList(quotesResponse)
                else
                    output.onError(response.message())
            }
        })
    }

}
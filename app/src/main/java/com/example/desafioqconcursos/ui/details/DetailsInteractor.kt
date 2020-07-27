package com.example.desafioqconcursos.ui.details

import com.example.desafioqconcursos.network.bean.SendVoteRequest
import com.example.desafioqconcursos.network.bean.SendVoteResponse
import com.example.desafioqconcursos.network.service.AppAPI
import com.example.desafioqconcursos.network.service.Service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class DetailsInteractor(val output: DetailsActivity) : DetailsContract.Interactor {

    private val api = Service.getRetrofitInstance().create(AppAPI::class.java)

    override fun sendVotes(sendVoteRequest: SendVoteRequest) {
        val call: Call<SendVoteResponse> = api.sendVote(sendVoteRequest)
        call.enqueue(object : Callback<SendVoteResponse> {
            override fun onFailure(call: Call<SendVoteResponse>, t: Throwable) {
                output.showMessage("Something went wrong! Check your connection.")
            }

            override fun onResponse(call: Call<SendVoteResponse>,response: Response<SendVoteResponse>) {
                val sendVoteResponse = response.body()

                if(sendVoteResponse is SendVoteResponse ){
                    if(response.isSuccessful){
                        output.showMessage("Your vote was successfully sent! :)")
                    } else {
                        output.showMessage(response.message())
                    }
                }  else {
                    output.showMessage("Oops, something went wrong!")
                }
            }
        })
    }
}
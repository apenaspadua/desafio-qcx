package com.example.desafioqconcursos.ui.details

import com.example.desafioqconcursos.network.bean.SendVoteRequest

interface DetailsContract {

    interface Interactor {
        fun sendVotes(sendVoteRequest: SendVoteRequest)
    }
}
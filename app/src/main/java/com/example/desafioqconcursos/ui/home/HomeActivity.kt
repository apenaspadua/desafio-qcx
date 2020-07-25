package com.example.desafioqconcursos.ui.home

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafioqconcursos.R
import com.example.desafioqconcursos.network.bean.QuotesResponse
import com.example.desafioqconcursos.ui.home.adapter.QuotesAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_home.*

@RequiresApi(Build.VERSION_CODES.M)
class HomeActivity : AppCompatActivity() {

    private lateinit var interactor: HomeContract.Interactor
    private lateinit var adapter: QuotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        interactor = HomeInteractor(this)
        getListQuotes()
    }

    private fun getListQuotes(){
        progressBar.visibility = View.VISIBLE
        interactor.getQuotes()
    }

    fun createList(list: List<QuotesResponse>) {
        progressBar.visibility = View.GONE
        val recyclerView = recycler_quotes
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = QuotesAdapter(list, this, this)
        recyclerView.adapter = adapter
    }

    fun onError(message: String) {
        Snackbar.make(
            findViewById(android.R.id.content),
            message,
            Snackbar.LENGTH_LONG
        ).show()
    }
}
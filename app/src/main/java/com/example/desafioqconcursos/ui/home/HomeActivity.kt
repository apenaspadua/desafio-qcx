package com.example.desafioqconcursos.ui.home

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.desafioqconcursos.R
import com.example.desafioqconcursos.network.bean.QuotesResponse
import com.example.desafioqconcursos.ui.details.DetailsActivity
import com.example.desafioqconcursos.ui.home.adapter.QuotesAdapter
import com.example.desafioqconcursos.utils.Utils
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_home.*

@RequiresApi(Build.VERSION_CODES.M)
class HomeActivity : AppCompatActivity() {

    private lateinit var interactor: HomeContract.Interactor
    private lateinit var adapter: QuotesAdapter
    private var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initializeComponents()

        interactor = HomeInteractor(this)
        getListQuotes(page)

        next.setOnClickListener(nextPage)
        prev.setOnClickListener(prevPage)
    }

    private val nextPage = View.OnClickListener {
        if (page < 26) {
            page += 1
            number_page.text = page.toString()
            getListQuotes(page)
        }
    }

    private val prevPage = View.OnClickListener {
        if (page > 1) {
            page -= 1
            number_page.text = page.toString()
            getListQuotes(page)
        }
    }

    private fun getListQuotes(page: Int){
        loading.visibility = View.VISIBLE
        interactor.getQuotes(page)
    }

    fun createList(list: List<QuotesResponse>) {
        loading.visibility = View.GONE
        val recyclerView = recycler_quotes
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = QuotesAdapter(list, this, this)
        recyclerView.adapter = adapter
    }

    fun onError(message: String) {
        loading.visibility = View.GONE
        Snackbar.make(
            findViewById(android.R.id.content),
            message,
            Snackbar.LENGTH_LONG
        ).show()
    }

    fun navigateToDetails(quotesResponse: QuotesResponse) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("quotes", quotesResponse)
        startActivity(intent)
    }

    private fun initializeComponents() {
        Utils.setPushDownAnimation(prev)
        Utils.setPushDownAnimation(next)

        Glide.with(this)
            .load(R.drawable.loading)
            .into(loading)
    }
}
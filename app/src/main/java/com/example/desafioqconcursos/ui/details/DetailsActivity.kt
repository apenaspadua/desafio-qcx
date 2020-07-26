package com.example.desafioqconcursos.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.desafioqconcursos.R
import com.example.desafioqconcursos.network.bean.QuotesResponse
import com.example.desafioqconcursos.utils.Utils
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_home.*

class DetailsActivity() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        initializeComponents()
        setValues()

        back.setOnClickListener(goBack)
    }

    private fun setValues() {
        val quotes = intent.getSerializableExtra("quotes") as QuotesResponse
        quote_description_details.text = quotes.en
        quote_author_details.text = quotes.author
        quote_rating_details.text = quotes.rating.toString()
    }

    private val goBack = View.OnClickListener {
        super.onBackPressed()
    }

    private fun initializeComponents() {
        Utils.setPushDownAnimation(back)
        Utils.setPushDownAnimation(button_send)
    }
}
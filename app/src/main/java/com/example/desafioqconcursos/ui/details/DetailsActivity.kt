package com.example.desafioqconcursos.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.example.desafioqconcursos.R
import com.example.desafioqconcursos.models.Quote
import com.example.desafioqconcursos.network.bean.QuotesResponse
import com.example.desafioqconcursos.network.bean.SendVoteRequest
import com.example.desafioqconcursos.utils.Utils
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_home.*

@Suppress("UNREACHABLE_CODE", "NAME_SHADOWING")
class DetailsActivity() : AppCompatActivity() {

    private lateinit var interactor: DetailsContract.Interactor
    private lateinit var quotes: QuotesResponse
    private var note: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        initializeComponents()
        setValues()

        interactor = DetailsInteractor(this)

        back.setOnClickListener(goBack)
        button_send.setOnClickListener(buttonSendClicked)

        radio_group.setOnCheckedChangeListener { _, checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            note = radio.text.toString().toInt()
        }
    }

    private fun setValues() {
        quotes = intent.getSerializableExtra("quotes") as QuotesResponse
        quote_description_details.text = quotes.en
        quote_author_details.text = quotes.author
        quote_rating_details.text = quotes.rating.toString()
    }

    private val buttonSendClicked =
        View.OnClickListener {
            if (note == 0) showMessage("Please select a value to rate.")
            else {
                progressBar.visibility = View.VISIBLE
                text_button.visibility = View.GONE
                val sendVoteRequest = quotes._id?.let { it -> SendVoteRequest(it, note) }
                sendVoteRequest?.let { it -> interactor.sendVotes(it) }
            }
        }

    fun showMessage(message: String) {
        progressBar.visibility = View.GONE
        text_button.visibility = View.VISIBLE
        Snackbar.make(
            findViewById(android.R.id.content),
            message,
            Snackbar.LENGTH_LONG
        ).show()
    }

    private val goBack = View.OnClickListener {
        super.onBackPressed()
    }

    private fun initializeComponents() {
        Utils.setPushDownAnimation(back)
        Utils.setPushDownAnimation(button_send)
    }
}
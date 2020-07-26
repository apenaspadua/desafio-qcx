package com.example.desafioqconcursos.ui.home.adapter

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.desafioqconcursos.R
import com.example.desafioqconcursos.network.bean.QuotesResponse
import com.example.desafioqconcursos.ui.details.DetailsActivity
import com.example.desafioqconcursos.ui.home.HomeActivity
import com.example.desafioqconcursos.utils.Utils
import kotlinx.android.synthetic.main.item_quotes.view.*

class QuotesAdapter (private val items: List<QuotesResponse>,
                     private val context: Context, private val activity: HomeActivity):
    RecyclerView.Adapter<QuotesAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_quotes, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return items.size
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.seeMore.let { Utils.setPushDownAnimation(it)}
        val quotes = items[position]

        holder.description?.text = quotes.en
        holder.author?.text = quotes.author
        holder.seeMore.setOnClickListener{
            activity.navigateToDetails(quotes)
        }
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val description: TextView? = itemView.quote_description
        val author: TextView? = itemView.quote_author
        val seeMore: ConstraintLayout = itemView.button_see_more
    }

}
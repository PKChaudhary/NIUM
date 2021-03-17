package com.abstractlearning.tutorials.nium.ui.main.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abstractlearning.tutorials.nium.ui.main.view.DetailsActivity
import com.abstractlearning.tutorials.nium.R
import com.abstractlearning.tutorials.nium.ui.main.adapter.MainAdapter.DataViewHolder
import com.abstractlearning.tutorials.nium.data.model.CountryDetails
import com.abstractlearning.tutorials.nium.svgdecoder.GlideToVectorYou
import com.abstractlearning.tutorials.nium.svgdecoder.GlideToVectorYouListener
import kotlinx.android.synthetic.main.item_layout.view.*

class MainAdapter(private val countryDetails: ArrayList<CountryDetails>) : RecyclerView.Adapter<DataViewHolder>() {
    lateinit var mcontext: Context
    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

      fun bind(countryDetails: CountryDetails) {
            itemView.apply {
                textViewCountryName.text = countryDetails.name
                textViewCapital.text = countryDetails.capital
                GlideToVectorYou
                    .init()
                    .with(imageViewAvatar.context)
                    .withListener(object : GlideToVectorYouListener{
                        override fun onLoadFailed() {

                        }

                        override fun onResourceReady() {

                        }
                    })
                    .load(Uri.parse(countryDetails.flag), imageViewAvatar)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false))

    override fun getItemCount(): Int =countryDetails.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(countryDetails[position])
        mcontext= holder.itemView.context
        holder.itemView.setOnClickListener {

            val intent = Intent(mcontext, DetailsActivity::class.java)
            intent.putExtra("flag_url", countryDetails[position].flag)
            intent.putExtra("country_name", countryDetails[position].name)
            intent.putExtra("capital_name", countryDetails[position].capital)
            mcontext.startActivity(intent)
            //Log.d("Selected:", countryFilterList[position])
        }
    }

    fun addCountries(countryDetails: List<CountryDetails>) {
        this.countryDetails.apply {
            clear()
            addAll(countryDetails)
        }

    }
}
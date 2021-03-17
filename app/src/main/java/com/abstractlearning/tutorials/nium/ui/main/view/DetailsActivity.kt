package com.abstractlearning.tutorials.nium.ui.main.view

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.abstractlearning.tutorials.nium.R
import com.abstractlearning.tutorials.nium.svgdecoder.GlideToVectorYou
import com.abstractlearning.tutorials.nium.svgdecoder.GlideToVectorYouListener
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.item_layout.view.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        textViewCountryName.text = "Country Name:  " + intent.extras!!.getString("country_name")!!
        if (intent.extras!!.getString("capital_name")!!.equals(""))
            textViewCapital.text = ""
        else
            textViewCapital.text = "Capital Name:  " + intent.extras!!.getString("capital_name")!!

        GlideToVectorYou
            .init()
            .with(imageViewAvatar.context)
            .withListener(object : GlideToVectorYouListener {
                override fun onLoadFailed() {

                }

                override fun onResourceReady() {

                }
            })
            .load(Uri.parse(intent.extras!!.getString("flag_url")!!), imageViewAvatar)
    }
}

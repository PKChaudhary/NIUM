package com.abstractlearning.tutorials.nium.ui.main.view

import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.abstractlearning.tutorials.nium.R.*
import com.abstractlearning.tutorials.nium.data.api.ApiHelper
import com.abstractlearning.tutorials.nium.data.api.RetrofitBuilder
import com.abstractlearning.tutorials.nium.data.model.User
import com.abstractlearning.tutorials.nium.ui.base.ViewModelFactory
import com.abstractlearning.tutorials.nium.ui.main.adapter.MainAdapter
import com.abstractlearning.tutorials.nium.ui.main.viewmodel.MainViewModel
import com.abstractlearning.tutorials.nium.utils.Status.ERROR
import com.abstractlearning.tutorials.nium.utils.Status.LOADING
import com.abstractlearning.tutorials.nium.utils.Status.SUCCESS
import com.abstractlearning.tutorials.nium.data.model.CountryDetails
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        setupViewModel()
        setupUI()
        setupObservers()

    }


    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.getCountryDetails().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data?.let { countryDetails -> retrieveList(countryDetails) }
                    }
                    ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveList(countryDetails: List<CountryDetails>) {

        btn_toggle.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                countryList(countryDetails.reversed())
                } else {
                countryList(countryDetails)
            }

        }
        countryList(countryDetails)
    }
    private fun countryList(countryDetails: List<CountryDetails>) {
        adapter.apply {
            addCountries(countryDetails)
            notifyDataSetChanged()
        }
    }
}

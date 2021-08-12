package com.example.countriesapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countriesapp.adapter.CountryAdapter
import com.example.countriesapp.databinding.FragmentFeedBinding
import com.example.countriesapp.viewmodel.FeedViewModel

class FeedFragment : Fragment() {
    private var _binding: FragmentFeedBinding? = null
    private val binding get() = _binding!!

    private val feedViewModel: FeedViewModel by viewModels<FeedViewModel>()
    private val countryAdapter = CountryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        feedViewModel.refreshData()

        binding.recyclerViewCountries.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewCountries.adapter = countryAdapter

        observeLiveData()
    }

    private fun observeLiveData() {
        feedViewModel.countries.observe(viewLifecycleOwner, Observer { countries ->

            countries?.let {
                binding.recyclerViewCountries.visibility = View.VISIBLE
                countryAdapter.updateCountryList(countries)
            }

        })

        feedViewModel.countryErrorMessage.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if (it) {
                    binding.textViewErrorMessage.visibility = View.VISIBLE
                } else {
                    binding.textViewErrorMessage.visibility = View.GONE
                }
            }
        })

        feedViewModel.countryLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if (it) {
                    binding.recyclerViewCountries.visibility = View.VISIBLE
                    binding.progressBarCountry.visibility = View.GONE
                    binding.textViewErrorMessage.visibility = View.GONE
                } else {
                    binding.progressBarCountry.visibility = View.GONE
                }
            }
        })
    }
}
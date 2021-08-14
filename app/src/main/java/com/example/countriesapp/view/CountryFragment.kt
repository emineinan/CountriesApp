package com.example.countriesapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.countriesapp.R
import com.example.countriesapp.databinding.FragmentCountryBinding
import com.example.countriesapp.databinding.FragmentFeedBinding
import com.example.countriesapp.util.downloadFromUrl
import com.example.countriesapp.util.placeholderProgressBar
import com.example.countriesapp.viewmodel.CountryViewModel

class CountryFragment : Fragment() {
    private var _binding: FragmentCountryBinding? = null
    private val binding get() = _binding!!

    private val countryViewModel: CountryViewModel by viewModels<CountryViewModel>()
    private var countryId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_country, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            countryId = CountryFragmentArgs.fromBundle(it).countryId
        }

        countryViewModel.getDataFromRoom(countryId)

        observeLiveData()
    }

    private fun observeLiveData() {
        countryViewModel.countryLiveData.observe(viewLifecycleOwner, Observer { country ->
            country?.let {
                binding.selectedCountry = country
            }
        })
    }
}
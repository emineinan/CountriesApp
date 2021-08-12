package com.example.countriesapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.countriesapp.databinding.RowItemBinding
import com.example.countriesapp.model.Country

class CountryAdapter : RecyclerView.Adapter<CountryAdapter.MyViewHolder>() {
    private var countryList = emptyList<Country>()

    class MyViewHolder(val binding: RowItemBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RowItemBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = countryList[position]
        holder.binding.textViewName.text = currentItem.countryName
        holder.binding.textViewRegion.text = currentItem.countryRegion
    }

    fun updateCountryList(newCountryList: List<Country>) {
        this.countryList = newCountryList
        notifyDataSetChanged()
    }
}
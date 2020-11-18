package com.hvd.farazpardazan.ui.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.hvd.farazpardazan.R
import com.hvd.farazpardazan.data.net.model.City
import com.hvd.farazpardazan.ui.adapter.CitiesAdapter
import com.hvd.farazpardazan.vm.fragment.CitiesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.cities_bottom_sheet.*

@AndroidEntryPoint
class CitiesFragmentBottomSheet(private val currentCity: City) : BaseFragmentBottomSheet() {

    private lateinit var viewModel : CitiesViewModel
    private var onCityPicked: ((CitiesFragmentBottomSheet, City) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cities_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(CitiesViewModel::class.java)

        viewModel.citiesData.observe(this) {
            initAdapter(it)
        }

    }

    private fun initAdapter(list: List<City>){
        val adapter = CitiesAdapter(list)

        adapter.selectedIndex = list.indexOf(currentCity)

        adapter.setOnSelectListener { selectableAdapter, i ->
            viewModel.changeCity(viewModel.citiesData.value!![i])
            dismiss()

            if (onCityPicked != null){
                onCityPicked!!(this, selectableAdapter.mItems[i])
            }
        }

        recyclerCities.adapter = adapter
    }

    fun onCityPicked(onCityPicked : ((CitiesFragmentBottomSheet, City) -> Unit)) {
        this.onCityPicked = onCityPicked
    }
}
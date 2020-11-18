package com.hvd.farazpardazan.ui.activity

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.hvd.farazpardazan.R
import com.hvd.farazpardazan.data.net.model.DailyWeather
import com.hvd.farazpardazan.data.net.model.HourlyWeather
import com.hvd.farazpardazan.data.net.model.ResOneCall
import com.hvd.farazpardazan.databinding.ActivityMainBinding
import com.hvd.farazpardazan.ui.adapter.DayAdapter
import com.hvd.farazpardazan.ui.adapter.SelectableAdapter
import com.hvd.farazpardazan.ui.adapter.WeekAdapter
import com.hvd.farazpardazan.ui.bottomsheet.CitiesFragmentBottomSheet
import com.hvd.farazpardazan.ui.state.DayState
import com.hvd.farazpardazan.ui.state.UIState
import com.hvd.farazpardazan.ui.viewholder.WeekViewHolder
import com.hvd.farazpardazan.util.ConditionHelper
import com.hvd.farazpardazan.vm.activity.MainActivityViewModel
import com.hvd.farazpardazan.vm.fragment.CitiesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.roundToInt


@AndroidEntryPoint
class MainActivity : ThemedActivity() {

    private lateinit var cityViewModel: CitiesViewModel
    private lateinit var mainViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        cityViewModel = ViewModelProvider(this).get(CitiesViewModel::class.java)

        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.vm = mainViewModel

        mainViewModel.weatherData.observe(this) {
            when (it) {
                is UIState.Progress -> loading()
                is UIState.Data<*> -> data(it as UIState.Data<ResOneCall>)
                is UIState.Error -> error(it.msg)
            }
        }
        mainViewModel.dayStateData.observe(this) { requestedDayState ->
            when (requestedDayState) {
                DayState.DAWN -> setThemeAndRefresh(R.style.Dawn)
                DayState.MORNING -> setThemeAndRefresh(R.style.Morning)
                DayState.NOON -> setThemeAndRefresh(R.style.Noon)
                DayState.EVENING -> setThemeAndRefresh(R.style.Evening)
                DayState.NIGHT -> setThemeAndRefresh(R.style.Night)
            }
        }

        mainViewModel.hourlyData.observe(this) {
            initDayAdapter(it)
        }

        cityViewModel.selectedCity.observe(this) {
            mainViewModel.changeCity(it)
            textCity.text = it.name
        }

        cityViewModel.selectDefaultCity()
        linCities.setOnClickListener {
            if (mainViewModel.weatherData.value == UIState.Progress) {
                return@setOnClickListener
            }

            val cityBottomSheet = CitiesFragmentBottomSheet(cityViewModel.selectedCity.value!!)
                cityBottomSheet.onCityPicked { citiesFragmentBottomSheet, city ->
                    cityViewModel.changeCity(city)
                }
            cityBottomSheet.show(supportFragmentManager, null)
        }
    }

    private fun loading() {
        linProgress.visibility = View.VISIBLE
    }

    private fun data(uiState: UIState.Data<ResOneCall>) {
        linProgress.visibility = View.GONE

        renderMainItems(uiState)
        initWeekAdapter(uiState)
    }

    private fun renderMainItems(uiState: UIState.Data<ResOneCall>) {
        val currentTemp = uiState.data.current.temp

        // not sure if these are correct or not
        val minTemp = uiState.data.daily[0].temp.min
        val maxTemp = uiState.data.daily[0].temp.max

        textAverageTemp.text = getString(R.string.celsiusDegree, currentTemp.roundToInt())
        textMinAndMax.text =
            getString(R.string.slashPlaceholder, maxTemp.roundToInt(), minTemp.roundToInt())

        val condition = uiState.data.current.weather[0].main
        imageBigIcon.setImageResource(
            ConditionHelper.getIconResByCondition(
                currentTheme,
                theme,
                condition
            )
        )
    }

    private fun initWeekAdapter(uiState: UIState.Data<ResOneCall>) {
        val adapter = WeekAdapter(uiState.data.daily)
        adapter.selectedIndex = 0
        adapter.setOnSelectListener { selectableAdapter: SelectableAdapter<DailyWeather, WeekViewHolder>, i: Int ->
            mainViewModel.changeSelectedDay(selectableAdapter.mItems[i])
        }
        recyclerWeek.adapter = adapter
    }

    private fun initDayAdapter(hourlyWeather: List<HourlyWeather>) {
        recyclerDay.adapter = DayAdapter(hourlyWeather)

        textEmptyHourlyData.visibility =
            if (hourlyWeather.isEmpty()) View.VISIBLE else View.INVISIBLE
    }

    private fun error(msg: String) {
        linProgress.visibility = View.GONE
    }
}
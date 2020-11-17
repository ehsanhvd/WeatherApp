package com.hvd.farazpardazan.ui.activity

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.hvd.farazpardazan.R
import com.hvd.farazpardazan.data.net.model.ResOneCall
import com.hvd.farazpardazan.databinding.ActivityMainBinding
import com.hvd.farazpardazan.ui.state.DayState
import com.hvd.farazpardazan.ui.state.UIState
import com.hvd.farazpardazan.util.getIconResByCondition
import com.hvd.farazpardazan.vm.activity.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import kotlin.math.roundToInt


@AndroidEntryPoint
class MainActivity : ThemedActivity() {

    @Inject
    @ApplicationContext
    lateinit var app: Context

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.vm = viewModel

        viewModel.weatherData.observe(this) {
            when (it) {
                is UIState.Progress -> loading()
                is UIState.Data<*> -> data(it as UIState.Data<ResOneCall>)
                is UIState.Error -> error(it.msg)
            }
        }
        viewModel.dayStateData.observe(this) { requestedDayState ->
            when (requestedDayState) {
                DayState.DAWN -> setThemeAndRefresh(R.style.Dawn)
                DayState.MORNING -> setThemeAndRefresh(R.style.Morning)
                DayState.NOON -> setThemeAndRefresh(R.style.Noon)
                DayState.EVENING -> setThemeAndRefresh(R.style.Evening)
                DayState.NIGHT -> setThemeAndRefresh(R.style.Night)
            }
        }

        imageBigIcon.setImageResource(theme.getIconResByCondition(""))
    }

    private fun loading() {
        linProgress.visibility = View.VISIBLE
    }

    private fun data(uiState: UIState.Data<ResOneCall>) {
        linProgress.visibility = View.GONE

        val currentTemp = uiState.data.current.temp

        // not sure if these are correct or not
        val minTemp = uiState.data.daily[0].temp.min
        val maxTemp = uiState.data.daily[0].temp.min

        textAverageTemp.text = getString(R.string.celsiusDegree, currentTemp.roundToInt())
        textMinAndMax.text = getString(R.string.slashPlaceholder, minTemp.roundToInt(), maxTemp.roundToInt())
    }

    private fun error(msg: String) {
        linProgress.visibility = View.GONE
    }
}
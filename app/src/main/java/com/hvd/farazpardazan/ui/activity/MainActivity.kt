package com.hvd.farazpardazan.ui.activity

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.hvd.farazpardazan.R
import com.hvd.farazpardazan.databinding.ActivityMainBinding
import com.hvd.farazpardazan.ui.state.DayState
import com.hvd.farazpardazan.ui.state.UIState
import com.hvd.farazpardazan.vm.activity.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : ThemedActivity() {

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
                UIState.Progress -> loading()
                else -> data()

            }
        }

        viewModel.dayStateData.observe(this) {requestedDayState ->
            when (requestedDayState) {
                DayState.DAWN -> setThemeAndRefresh(R.style.Dawn)
                DayState.MORNING -> setThemeAndRefresh(R.style.Morning)
                DayState.NOON -> setThemeAndRefresh(R.style.Noon)
                DayState.EVENING -> setThemeAndRefresh(R.style.Evening)
                DayState.NIGHT -> setThemeAndRefresh(R.style.Night)
            }
        }
    }

    private fun loading() {
        linProgress.visibility = View.VISIBLE
    }

    private fun data() {
        linProgress.visibility = View.GONE
    }
}
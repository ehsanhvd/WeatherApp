package com.hvd.farazpardazan.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.hvd.farazpardazan.R
import com.hvd.farazpardazan.databinding.ActivityMainBinding
import com.hvd.farazpardazan.ui.state.UIState
import com.hvd.farazpardazan.vm.activity.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: MainActivityViewModel =
            ViewModelProvider(this).get(MainActivityViewModel::class.java)

        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.vm = viewModel

        viewModel.navData.observe(this) {
            when (it) {
                UIState.Progress -> loading()
                else -> data()

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
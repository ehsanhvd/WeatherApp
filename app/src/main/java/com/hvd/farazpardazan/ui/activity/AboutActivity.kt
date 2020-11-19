package com.hvd.farazpardazan.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hvd.farazpardazan.R
import com.hvd.farazpardazan.util.CommonUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.about_activity.*

@AndroidEntryPoint
class AboutActivity : AppCompatActivity() {
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, AboutActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.about_activity)

        imgGithub.setOnClickListener {
            CommonUtils.openUrl(this, "https://github.com/ehsanhvd/WeatherApp")
        }

        imgLinkedIn.setOnClickListener {
            CommonUtils.openUrl(this, "https://www.linkedin.com/in/ehsanhasanvand/")
        }
    }
}
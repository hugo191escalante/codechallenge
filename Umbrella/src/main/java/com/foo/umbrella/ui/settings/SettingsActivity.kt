package com.foo.umbrella.ui.settings

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.foo.umbrella.R
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    // TODO: Use rx-preferences in a separate module/repository to observe SharedPreferences changes notifying both ForecastActivity and SettingsActivity
    // TODO: Receive the preferences using the v7.preference library
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        initViews()
    }

    private fun initViews() {
        setSupportActionBar(a_settings_toolbar)
        supportActionBar?.title = "Settings"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    companion object {
        fun newActivity(context: Context) {
            val intent = Intent(context, SettingsActivity::class.java)
            context.startActivity(intent)
        }
    }
}

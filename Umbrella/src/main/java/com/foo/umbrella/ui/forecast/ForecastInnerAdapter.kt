package com.foo.umbrella.ui.forecast

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.foo.umbrella.R
import com.foo.umbrella.data.model.entities.ForecastCondition
import kotlinx.android.synthetic.main.recycler_forecast_inner.view.*

/**
 * Created by user on 9/21/17.
 */
class ForecastInnerAdapter(private val forecastConditions: List<ForecastCondition>, private val isCelsius: Boolean) : RecyclerView.Adapter<ForecastInnerAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(forecastConditions[position], isCelsius)

    override fun getItemCount(): Int = forecastConditions.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent?.context)
        val view: View = layoutInflater.inflate(R.layout.recycler_forecast_inner, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(forecastCondition: ForecastCondition, isCelsius: Boolean) = with(itemView) {
            r_inner_time.text = forecastCondition.displayTime
            r_inner_icon.setImageDrawable(retrieveIcon(forecastCondition.icon, context))

            when (isCelsius) {
                true -> r_inner_temperature.text = "${forecastCondition.tempCelsius} °"
                false -> r_inner_temperature.text = "${forecastCondition.tempFahrenheit} °"
            }
        }

        private fun retrieveIcon(icon: String, context: Context): Drawable =
                when (icon) {
                    "cloudy" -> ContextCompat.getDrawable(context, R.drawable.weather_cloudy)
                    "fog" -> ContextCompat.getDrawable(context, R.drawable.weather_fog)
                    "hail" -> ContextCompat.getDrawable(context, R.drawable.weather_hail)
                    "lightning" -> ContextCompat.getDrawable(context, R.drawable.weather_lightning)
                    "lightning_rainy" -> ContextCompat.getDrawable(context, R.drawable.weather_lightning_rainy)
                    "partlycloudy" -> ContextCompat.getDrawable(context, R.drawable.weather_partlycloudy)
                    "rainy" -> ContextCompat.getDrawable(context, R.drawable.weather_rainy)
                    "snowy" -> ContextCompat.getDrawable(context, R.drawable.weather_snowy)
                    "snowy_rainy" -> ContextCompat.getDrawable(context, R.drawable.weather_snowy_rainy)
                    "windy_variant" -> ContextCompat.getDrawable(context, R.drawable.weather_windy_variant)
                    else -> ContextCompat.getDrawable(context, R.drawable.weather_sunny)
                }
    }
}
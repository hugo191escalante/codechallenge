package com.foo.umbrella.ui.forecast

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.foo.umbrella.R
import com.foo.umbrella.data.model.entities.ForecastConditionDay
import kotlinx.android.synthetic.main.recycler_forecast_day.view.*

/**
 * Created by user on 9/21/17.
 */

class ForecastAdapter(private val forecastConditions: List<ForecastConditionDay>, private val isCelsius: Boolean) : RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {

    override fun getItemCount(): Int = forecastConditions.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(forecastConditions[position], isCelsius)

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent?.context)
        val view: View = layoutInflater.inflate(R.layout.recycler_forecast_day, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(forecastConditionDay: ForecastConditionDay, isCelsius: Boolean) = with(itemView) {
            // TODO: Show 'Today', 'Yesterday' labels using DateUtils.getRelativeTimeSpanString()
            r_forecast_txt.text = forecastConditionDay.day.toString()

            val forecastInnerAdapter = ForecastInnerAdapter(forecastConditionDay.forecastConditions, isCelsius)
            r_forecast_recycler.layoutManager = GridLayoutManager(itemView.context, 4, GridLayoutManager.VERTICAL, false)
            r_forecast_recycler.adapter = forecastInnerAdapter
        }
    }
}
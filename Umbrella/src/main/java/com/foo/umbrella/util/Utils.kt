package com.foo.umbrella.util

import com.foo.umbrella.data.model.entities.ForecastCondition
import com.foo.umbrella.data.model.entities.ForecastConditionDay

class Utils {
    companion object {
        fun parseForecastConditions(forecastConditions: MutableList<ForecastCondition>): List<ForecastConditionDay> {
            val result: ArrayList<ForecastConditionDay> = ArrayList()
            val dateMap: HashMap<Int, ArrayList<ForecastCondition>> = fillMap(forecastConditions)

            for ((_, value) in dateMap) {
                val minEntry = value.minBy { forecastCondition -> forecastCondition.tempCelsius }
                val maxEntry = value.maxBy { forecastCondition -> forecastCondition.tempCelsius }

                result.add(ForecastConditionDay(value[0].dateTime.toLocalDate(), value, minEntry, maxEntry))
            }

            result.sortByDescending { it.day }
            return result
        }

        private fun fillMap(forecastConditions: MutableList<ForecastCondition>): HashMap<Int, ArrayList<ForecastCondition>> {
            val dateMap: HashMap<Int, ArrayList<ForecastCondition>> = HashMap()

            for (forecastCondition in forecastConditions) {
                val dayOfYear = forecastCondition.dateTime.dayOfYear

                var listForecast = dateMap[dayOfYear]
                if (listForecast == null) {
                    listForecast = ArrayList()
                    dateMap.put(dayOfYear, listForecast)
                }

                listForecast.add(forecastCondition)
            }

            return dateMap
        }
    }
}
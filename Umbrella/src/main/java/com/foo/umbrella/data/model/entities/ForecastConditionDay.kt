package com.foo.umbrella.data.model.entities

/**
 * Created by user on 9/21/17.
 */

data class ForecastConditionDay(val day: String, val forecastConditions: List<ForecastCondition>, val coolestHour: ForecastCondition?, val warmestHour: ForecastCondition?)

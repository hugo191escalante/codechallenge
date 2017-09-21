package com.foo.umbrella.data.model.entities

import org.threeten.bp.LocalDate

/**
 * Created by user on 9/21/17.
 */

data class ForecastConditionDay(val day: LocalDate, val forecastConditions: List<ForecastCondition>, val coolestHour: ForecastCondition?, val warmestHour: ForecastCondition?)

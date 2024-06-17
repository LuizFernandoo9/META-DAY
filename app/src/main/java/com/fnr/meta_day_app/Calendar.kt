// app/src/main/java/com/example/calendar/Calendar.kt

package com.fnr.meta_day_app

data class Calendar(
    val id: Int,
    val date: String,
    val events: List<String>
)
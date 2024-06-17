// app/src/main/java/com/example/calendar/CalendarDAO.kt

package com.example.calendar

import android.content.Context
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fnr.meta_day_app.Calendar

@Dao
interface CalendarDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCalendar(calendar: Calendar)

    @Query("SELECT * FROM calendars")
    fun getCalendars(): List<Calendar>

    @Query("SELECT * FROM calendars WHERE date = :date")
    fun getCalendarsByDate(date: String): List<Calendar>
}
// app/src/main/java/com/example/calendar/CalendarActivity.kt

package com.example.calendar

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fnr.meta_day_app.R

class CalendarActivity : AppCompatActivity() {
    private lateinit var calendarDAO: CalendarDAO
    private lateinit var calendarAdapter: CalendarAdapter<Any?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        calendarDAO = CalendarDAO(getApplication())
        calendarAdapter = CalendarAdapter(this, calendarDAO)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = calendarAdapter

        calendarAdapter.setOnItemClickListener { calendar ->
            Toast.makeText(this, "Calendar selected: ${calendar.date}", Toast.LENGTH_SHORT).show()
        }
    }
}
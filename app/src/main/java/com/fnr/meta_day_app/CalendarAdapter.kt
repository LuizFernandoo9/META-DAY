// app/src/main/java/com/example/calendar/CalendarAdapter.kt

package com.example.calendar

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fnr.meta_day_app.R

private val <Calendar> Calendar.date: CharSequence?
    get() {}

class CalendarAdapter<Calendar>(private val context: Context, private val calendars: List<Calendar>) :
    RecyclerView.Adapter<CalendarAdapter<Any?>.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.textView)

        init {
            textView.setOnClickListener { itemView.context }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_calendar, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = calendars[position].date
    }

    override fun getItemCount(): Int {
        return calendars.size
    }

    fun setOnItemClickListener(any: Any) {

    }
}
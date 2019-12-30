package com.example.senthil.kotlin_tablayout

import android.graphics.Color
import android.icu.util.Calendar
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.senthil.kotlin_tablayout.Fragment.AccountBookFragment
import kotlinx.android.synthetic.main.item_calendar.view.*


class CalendarAdapter(private val mainActivity: AccountBookFragment, val itemClick: (Int, Int) -> Unit) : RecyclerView.Adapter<ViewHolderHelper>() {

    private val baseCalendar = BaseCalendar()

    init {
        baseCalendar.initBaseCalendar { refreshView(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderHelper {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_calendar, parent, false)

        return ViewHolderHelper(view, itemClick)
    }

    override fun getItemCount(): Int {
        return BaseCalendar.LOW_OF_CALENDAR * BaseCalendar.DAYS_OF_WEEK
    }

    override fun onBindViewHolder(holder: ViewHolderHelper, position: Int) {
        var month = baseCalendar.calendar.get(Calendar.MONTH) + 1
        var day = baseCalendar.data[position]

        if (position % BaseCalendar.DAYS_OF_WEEK == 0)
            holder.containerView.tv_date.setTextColor(Color.parseColor("#ff1200"))
        else holder.containerView.tv_date.setTextColor(Color.parseColor("#676d6e"))

        if (position < baseCalendar.prevMonthTailOffset) {
            holder.containerView.tv_date.alpha = 0.3f
            month --
        }
        else if (position >= baseCalendar.prevMonthTailOffset + baseCalendar.currentMonthMaxDate) {
            holder.containerView.tv_date.alpha = 0.3f
            month ++
        }
        else holder.containerView.tv_date.alpha = 1f

        if (month == 0) month = 12
        if (month == 13) month = 1

        holder.containerView.tv_date.text = baseCalendar.data[position].toString()

        holder.containerView.setOnClickListener { itemClick(month, day) }
    }

    fun changeToPrevMonth() {
        baseCalendar.changeToPrevMonth { refreshView(it) }
    }

    fun changeToNextMonth() {
        baseCalendar.changeToNextMonth { refreshView(it) }
    }

    private fun refreshView(calendar: Calendar) {
        notifyDataSetChanged()
        mainActivity.refreshCurrentMonth(calendar)
    }
}
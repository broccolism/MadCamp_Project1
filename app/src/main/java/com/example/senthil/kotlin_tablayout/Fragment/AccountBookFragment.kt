package com.example.senthil.kotlin_tablayout.Fragment

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.senthil.kotlin_tablayout.BaseCalendar
import com.example.senthil.kotlin_tablayout.CalendarAdapter

import com.example.senthil.kotlin_tablayout.R
import kotlinx.android.synthetic.main.fragment_account_book.*
import kotlinx.android.synthetic.main.fragment_account_book.view.*
import java.util.*

class AccountBookFragment : Fragment() {
    lateinit var calendarAdapter: CalendarAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        calendarAdapter = CalendarAdapter(this) {
            month, day -> Log.e("POSITION", "${month} / ${day}")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_account_book, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tv_prev_month?.setOnClickListener { calendarAdapter.changeToPrevMonth() }
        tv_next_month?.setOnClickListener { calendarAdapter.changeToNextMonth() }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rv_calendar?.layoutManager = GridLayoutManager(this.context, BaseCalendar.DAYS_OF_WEEK)
        rv_calendar?.adapter = calendarAdapter
        rv_calendar?.addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.HORIZONTAL))
        rv_calendar?.addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))

        //rv_calendar.setHasFixedSize(true)
    }

    fun refreshCurrentMonth(calendar: Calendar) {
        val sdf = SimpleDateFormat("yyyy MM", Locale.KOREAN)
        tv_current_month?.text = sdf.format(calendar.time)
    }
}

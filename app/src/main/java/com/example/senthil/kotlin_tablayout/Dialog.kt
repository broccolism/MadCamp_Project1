package com.example.senthil.kotlin_tablayout

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.dialog_item.*
import kotlinx.android.synthetic.main.dialog_list.*
import java.util.ArrayList

class Dialog : AppCompatActivity() {

    internal lateinit var db : DBHelper
    lateinit var dialogAdapter: DialogAdapter
    private var listConsumptions: List<Consumption> = ArrayList<Consumption>()

    private val items: ArrayList<Consumption> = arrayListOf(
            Consumption(1, 10000, 1, "jack", "2019/12/31 17:00"),
            Consumption(2, 6000, -1, "dinner", "2019/12/31 19:00"),
            Consumption(3, 2000, -1, "yogurt", "2019/12/31 19:30")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_list)

        db = DBHelper(this)

        // after constructing db, use listConsumptions
        dialogAdapter = DialogAdapter(items, (
                fun(consumption : Consumption) {
                    val intent: Intent = Intent(this, ModifyActivity::class.java)
                    intent.putExtra("consumption", consumption)

                    startActivity(intent)
                }))

        refreshData()

        val intent = getIntent()
        val month = intent.getIntExtra("month", 0)
        val day = intent.getIntExtra("day", 0)

        dialog_list_month.setText(month.toString())
        dialog_list_day.setText(day.toString())

        //For FAB
        val dialog_FAB = findViewById<FloatingActionButton>(R.id.dialog_fab)
        dialog_FAB.setOnClickListener {
            val next = Intent(this, AccountAdder::class.java)
            next.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            next.putExtra("month", month)
            next.putExtra("day", day)
            startActivity(next)
        }
    }

    private fun refreshData() {
        rv_dialog?.layoutManager = LinearLayoutManager(baseContext)
        rv_dialog?.adapter = dialogAdapter
        listConsumptions = db.dbList
    }
}
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
import kotlinx.android.synthetic.main.dialog_list.*
import java.util.ArrayList

class Dialog() : AppCompatActivity()
{
    private val items: ArrayList<AccountValue> = arrayListOf(
            AccountValue("TESTING0", "9999"),
            AccountValue("TESTING1", "10000"),
            AccountValue("TESTING2", "22220"),
            AccountValue("TESTING3", "22520"),
            AccountValue("TESTING4", "22220"),
            AccountValue("TESTING5", "22220"),
            AccountValue("TESTING6", "22220"),
            AccountValue("TESTING7", "22220"),
            AccountValue("TESTING8", "22220"),
            AccountValue("TESTING9", "22220"),
            AccountValue("TESTING10", "22220"),
            AccountValue("TESTING11", "22220"),
            AccountValue("TESTING12", "22220"),
            AccountValue("TESTING13", "22220"),
            AccountValue("TESTING14", "22220"),
            AccountValue("TESTING15", "22220"),
            AccountValue("TESTING16", "22220"),
            AccountValue("TESTING17", "22220"),
            AccountValue("TESTING18", "22220"),
            AccountValue("TESTING19", "22220")
    )

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_list)

        rv_dialog?.layoutManager = LinearLayoutManager(baseContext)
        rv_dialog?.adapter = DialogAdapter(items)

        val intent = getIntent()
        val month = intent.getIntExtra("month", 0)
        val day = intent.getIntExtra("day", 0)

        findViewById<TextView>(R.id.dialog_list_month).setText(month.toString())
        findViewById<TextView>(R.id.dialog_list_day).setText(day.toString())

        //For FAB
        val dialog_FAB = findViewById<FloatingActionButton>(R.id.dialog_fab)
        dialog_FAB.setOnClickListener{ view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

}
package com.example.senthil.kotlin_tablayout

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.account_item_adder.*
import kotlinx.android.synthetic.main.dialog_list.*
import java.util.*

class AccountAdder : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.account_item_adder)

        val intent = getIntent()
        val month = intent.getIntExtra("month", 0)
        val day = intent.getIntExtra("day", 0)

        adder_date_month.setText(month.toString())
        adder_date_day.setText(day.toString())




    }

    //액티비티 종료 될 때 애니메이션 없애는 부분.
    // 근데 종료시에는 애니메이션이 있는게 나을거같아서 주석처리함.
    /*override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)
    }*/
}
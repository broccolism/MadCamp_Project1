package com.example.senthil.kotlin_tablayout

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import kotlinx.android.synthetic.main.account_item_adder.*
import kotlinx.android.synthetic.main.account_item_editor.*
import kotlinx.android.synthetic.main.dialog_list.*
import java.util.*

class AccountAdder : AppCompatActivity() {
    private var updated_money_flag = false
    private var updated_usage_flag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.account_item_adder)

        val intent = getIntent()
        val month = intent.getIntExtra("month", 0)
        val day = intent.getIntExtra("day", 0)
        val max_id = intent.getIntExtra("max_id", 0)

        adder_date_month.setText(month.toString())
        adder_date_day.setText(day.toString())


        var inOrOut = false

        adder_money.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                updated_money_flag = true
                Log.e("MONEY", s.toString())
            }
        })

        adder_usage.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                updated_usage_flag = true
                Log.e("USAGE", s.toString())
            }
        })

        adder_radioGroup.setOnCheckedChangeListener { adder_radioGroup, i ->
            when(i) {
                R.id.adder_income -> {
                    inOrOut = true
                    Log.e("INCOME", "$inOrOut")
                }
                R.id.adder_outcome -> {
                    inOrOut = false
                    Log.e("INCOME", "$inOrOut")
                }
            }
        }

        // add
        add_new_account_item.setOnClickListener {
            val intent = Intent()

            val id = max_id + 1
            val money = Integer.parseInt(adder_money?.text.toString())
            val pm = if(inOrOut) 1 else -1
            val usage = adder_usage?.text.toString()
            val time = ""

            val consumption = Consumption(id, money, pm, usage, time)
            intent.putExtra("addConsumption", consumption)
            setResult(Activity.RESULT_OK, intent)
        }
    }



    //액티비티 종료 될 때 애니메이션 없애는 부분.
    // 근데 종료시에는 애니메이션이 있는게 나을거같아서 주석처리함.
    /*override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)
    }*/
}
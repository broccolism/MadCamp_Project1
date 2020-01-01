package com.example.senthil.kotlin_tablayout

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.account_item_adder.*
import kotlinx.android.synthetic.main.account_item_editor.*
import kotlinx.android.synthetic.main.activity_modify.*
import java.util.zip.Inflater

class ModifyActivity : AppCompatActivity() {
    private var updated_money_flag = false
    private var updated_usage_flag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.account_item_editor)

        val intent = getIntent()

        //200101
        val money = this.editor_money
        val usage = this.editor_usage
        val time = this.editor_time_textView

        money.setHint(intent.getStringExtra("money"))
        usage.setHint(intent.getStringExtra("usage"))

        val tmp_time = intent.getStringExtra("time") //"YYYY/MM/DD_HH:MM"
        val hour = tmp_time!!.slice(IntRange(11, 12))
        val minute = tmp_time!!.slice(IntRange(14, 15))
        time.text = "at " + hour + " : " + minute
        //END

        val consumption = intent.getParcelableExtra<Consumption>("consumption")

        var inOrOut = false

        editor_money.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                updated_money_flag = true
                Log.e("MONEY", s.toString())
            }
        })

        editor_usage.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                updated_usage_flag = true
                Log.e("USAGE", s.toString())
            }
        })

        editor_radioGroup.setOnCheckedChangeListener { editor_radioGroup, i ->
            when(i) {
                R.id.editor_income -> {
                    inOrOut = true
                    Log.e("INCOME", "$inOrOut")
                }
                R.id.editor_outcome -> {
                    inOrOut = false
                    Log.e("INCOME", "$inOrOut")
                }
            }
        }

        edit_save_account_item.setOnClickListener {
            val intent = Intent()

            val id = consumption.id
            val money = Integer.parseInt(editor_money?.text.toString())
            val pm = if(inOrOut) 1 else -1
            val usage = editor_usage?.text.toString()
            val time = consumption.time

            val consumption = Consumption(id, money, pm, usage, time)
            intent.putExtra("updateConsumption", consumption)
            setResult(Activity.RESULT_OK, intent)
        }

        edit_delete_account_item.setOnClickListener {
            val intent = Intent()

            val id = consumption.id
            val money = consumption.money
            val pm = if(inOrOut) 1 else -1
            val usage = editor_usage?.text.toString()
            val time = consumption.time

            val consumption = Consumption(id, money, pm, usage, time)
            intent.putExtra("deleteConsumption", consumption)
            setResult(Activity.RESULT_FIRST_USER, intent)

            finish()
        }
    }
}
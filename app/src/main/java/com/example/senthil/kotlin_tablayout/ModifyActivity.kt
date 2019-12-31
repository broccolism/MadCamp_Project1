package com.example.senthil.kotlin_tablayout

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_modify.*

class ModifyActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify)

        var consumption = intent.getParcelableExtra<Consumption>("consumption")

        modify.text = consumption.usage
    }
}
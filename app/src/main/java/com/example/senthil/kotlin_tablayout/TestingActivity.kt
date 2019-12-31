package com.example.senthil.kotlin_tablayout

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import kotlinx.android.synthetic.main.testing.*

class TestingActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.testing)
        var text = findViewById<TextView>(R.id.testing_dialog)
        text.text = "HI"

    }


}
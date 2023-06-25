package com.group2_comp304lab3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Exercise2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise2)

        val dateTextView : DateTextView = findViewById(R.id.dateTextView)
        dateTextView.formatLongDate(true)
    }
}
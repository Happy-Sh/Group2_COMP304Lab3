package com.group2_comp304lab3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val ex1 = findViewById<TextView>(R.id.exercise1TextView)
        val ex2 = findViewById<TextView>(R.id.exercise2TextView)
        val ex3 = findViewById<TextView>(R.id.exercise3TextView)

        ex1.setOnClickListener{
            val intent = Intent(this,Exercise1 ::class.java)
            startActivity(intent)
        }
        ex2.setOnClickListener{
            val intent = Intent(this,Exercise2 ::class.java)
            startActivity(intent)
        }
        ex3.setOnClickListener{
            val intent = Intent(this,Exercise3 ::class.java)
            startActivity(intent)
        }
    }
}
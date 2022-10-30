package com.example.mls_view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class ResultsActivity : AppCompatActivity() {

    private lateinit var text11 : TextView
    private lateinit var text21 : TextView
    private lateinit var text31 : TextView
    private lateinit var text41 : TextView
    private lateinit var text51 : TextView
    private lateinit var text61 : TextView
    private lateinit var text71 : TextView

    private lateinit var text12 : TextView
    private lateinit var text22 : TextView
    private lateinit var text32 : TextView
    private lateinit var text42 : TextView
    private lateinit var text52 : TextView
    private lateinit var text62 : TextView
    private lateinit var text72 : TextView

    private lateinit var btnFinish: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        init()
        setTextView()
    }
    private fun init(){
        text11 = findViewById(R.id.text11)
        text21 = findViewById(R.id.text21)
        text31 = findViewById(R.id.text31)
        text41 = findViewById(R.id.text41)
        text51 = findViewById(R.id.text51)
        text61 = findViewById(R.id.text61)
        text71 = findViewById(R.id.text71)

        text12 = findViewById(R.id.text12)
        text22 = findViewById(R.id.text22)
        text32 = findViewById(R.id.text32)
        text42 = findViewById(R.id.text42)
        text52 = findViewById(R.id.text52)
        text62 = findViewById(R.id.text62)
        text72 = findViewById(R.id.text72)

        btnFinish = findViewById(R.id.btnFinish)
    }

    private fun setTextView(){
        text11.text = SearchRep.text11
        text21.text = SearchRep.text21
        text31.text = SearchRep.text31
        text41.text = SearchRep.text41
        text51.text = SearchRep.text51
        text61.text = SearchRep.text61
        text71.text = SearchRep.text71
    }
}
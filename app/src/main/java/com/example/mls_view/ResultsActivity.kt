package com.example.mls_view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
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

    private lateinit var linearLayout:LinearLayout
    private lateinit var linearLayout2:LinearLayout
    private lateinit var linearLayout3:LinearLayout
    private lateinit var linearLayout4:LinearLayout
    private lateinit var linearLayout5:LinearLayout
    private lateinit var linearLayout6:LinearLayout
    private lateinit var linearLayout7:LinearLayout

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

        linearLayout = findViewById(R.id.linearLayout)
        linearLayout2 = findViewById(R.id.linearLayout2)
        linearLayout3 = findViewById(R.id.linearLayout3)
        linearLayout4 = findViewById(R.id.linearLayout4)
        linearLayout5 = findViewById(R.id.linearLayout5)
        linearLayout6 = findViewById(R.id.linearLayout6)
        linearLayout7 = findViewById(R.id.linearLayout7)
    }

    private fun setTextView(){
        text11.text = SearchRep.text11
        text21.text = SearchRep.text21
        text31.text = SearchRep.text31
        text41.text = SearchRep.text41
        text51.text = SearchRep.text51
        text61.text = SearchRep.text61
        text71.text = SearchRep.text71

        text12.text = SearchRep.response.getJSONObject("sector").getString("value")
        if ( SearchRep.response.getJSONObject("sector").getString("check") == "true"){
            linearLayout.setBackgroundResource(R.drawable.layout_radius2_2)
        }else{
            linearLayout.setBackgroundResource(R.drawable.layout_radius2)
        }
        text22.text = SearchRep.response.getJSONObject("subSector").getString("value")
        if ( SearchRep.response.getJSONObject("subSector").getString("check") == "true"){
            linearLayout2.setBackgroundResource(R.drawable.layout_radius2_2)
        }else{
            linearLayout2.setBackgroundResource(R.drawable.layout_radius2)
        }
        text32.text = SearchRep.response.getJSONObject("technologies1Lvl").getString("value")
        if ( SearchRep.response.getJSONObject("technologies1Lvl").getString("check") == "true"){
            linearLayout3.setBackgroundResource(R.drawable.layout_radius2_2)
        }else{
            linearLayout3.setBackgroundResource(R.drawable.layout_radius2)
        }
        text42.text = SearchRep.response.getJSONObject("technologies2Lvl").getString("value")
        if ( SearchRep.response.getJSONObject("technologies2Lvl").getString("check") == "true"){
            linearLayout4.setBackgroundResource(R.drawable.layout_radius2_2)
        }else{
            linearLayout4.setBackgroundResource(R.drawable.layout_radius2)
        }
        text52.text = SearchRep.response.getJSONObject("technologies3Lvl").getString("value")
        if ( SearchRep.response.getJSONObject("technologies3Lvl").getString("check") == "true"){
            linearLayout5.setBackgroundResource(R.drawable.layout_radius2_2)
        }else{
            linearLayout5.setBackgroundResource(R.drawable.layout_radius2)
        }
        text62.text = SearchRep.response.getJSONObject("okpd2").getString("value")
        if ( SearchRep.response.getJSONObject("okpd2").getString("check") == "true"){
            linearLayout6.setBackgroundResource(R.drawable.layout_radius2_2)
        }else{
            linearLayout6.setBackgroundResource(R.drawable.layout_radius2)
        }
        text72.text = SearchRep.response.getJSONObject("description").getString("value")
        if ( SearchRep.response.getJSONObject("description").getString("check") == "true"){
            linearLayout7.setBackgroundResource(R.drawable.layout_radius2_2)
        }else{
            linearLayout7.setBackgroundResource(R.drawable.layout_radius2)
        }
    }
}
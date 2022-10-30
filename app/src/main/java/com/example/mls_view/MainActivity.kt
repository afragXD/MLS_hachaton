package com.example.mls_view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private val url = "https://searchplatform.rospatent.gov.ru/patsearch/v0.2/search"
    private val key = "26a213594e7f4f6e8cd89064d885ea93"

    private lateinit var progressBar : ProgressBar
    private lateinit var editText1 : EditText
    private lateinit var editText2 : EditText
    private lateinit var editText3 : EditText
    private lateinit var editText4 : EditText
    private lateinit var editText5 : EditText
    private lateinit var editText6 : EditText
    private lateinit var editText7 : EditText
    private lateinit var btnSearch : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        btnSearch = findViewById(R.id.btnSearch)
        btnSearch.setOnClickListener(View.OnClickListener {
            check()
        })
    }

    private fun init(){
        editText1 = findViewById(R.id.editText1)
        editText2 = findViewById(R.id.editText2)
        editText3 = findViewById(R.id.editText3)
        editText4 = findViewById(R.id.editText4)
        editText5 = findViewById(R.id.editText5)
        editText6 = findViewById(R.id.editText6)
        editText7 = findViewById(R.id.editText7)

        progressBar = findViewById(R.id.progressBar)
    }

    private fun setAndStart(){
        SearchRep.text11 = editText1.text.toString()
        SearchRep.text21 = editText2.text.toString()
        SearchRep.text31 = editText3.text.toString()
        SearchRep.text41 = editText4.text.toString()
        SearchRep.text51 = editText5.text.toString()
        SearchRep.text61 = editText6.text.toString()
        SearchRep.text71 = editText7.text.toString()

        val  i = Intent(this, ResultsActivity::class.java)
        startActivity(i)
    }

    private fun check(){
        if (editText1.text.isNullOrBlank() || editText2.text.isNullOrBlank() || editText3.text.isNullOrBlank()
            || editText4.text.isNullOrBlank() || editText5.text.isNullOrBlank() || editText6.text.isNullOrBlank() || editText7.text.isNullOrBlank()
        ){
            Toast.makeText(this, "Заполните поля", Toast.LENGTH_SHORT).show()
        }else{
            setAndStart()
        }
    }
    private fun post(input:String){
        val postData = JSONObject()
        try {
            postData.put("qn", input)
            postData.put("limit", 10)
        } catch (e: JSONException){
            Log.d("MyLog", e.toString())
        }
        val requestQueue = Volley.newRequestQueue(this)
        val stringRequest = object: JsonObjectRequest(Method.POST, url, postData, { response ->
            try {
                progressBar.visibility = View.GONE
                val  i = Intent(this, ResultsActivity::class.java)
                startActivity(i)
            } catch (e : JSONException){
                Log.d("MyLog", e.toString())
                progressBar.visibility = View.GONE
            }
        }, {
                error ->
            Log.d("MyLog", error.toString())
            progressBar.visibility = View.GONE
        }){
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Authorization"] = "Bearer $key"
                headers["Content-Type"] = "application/json"
                return headers
            }
        }
        requestQueue.add(stringRequest)
    }
}
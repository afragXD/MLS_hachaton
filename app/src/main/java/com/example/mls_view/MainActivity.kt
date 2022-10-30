package com.example.mls_view

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
import android.content.Intent.FLAG_GRANT_WRITE_URI_PERMISSION
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import java.io.File

class MainActivity : AppCompatActivity() {

    private val url = "http://194.67.121.96:5000/processing"

    private lateinit var progressBar : ProgressBar
    private lateinit var editText1 : EditText
    private lateinit var editText2 : EditText
    private lateinit var editText3 : EditText
    private lateinit var editText4 : EditText
    private lateinit var editText5 : EditText
    private lateinit var editText6 : EditText
    private lateinit var editText7 : EditText
    private lateinit var btnSearch : Button
    private lateinit var text1:String
    private lateinit var text2:String
    private lateinit var text3:String
    private lateinit var text4:String
    private lateinit var text5:String
    private lateinit var text6:String
    private lateinit var text7:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
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

        btnSearch = findViewById(R.id.btnSearch)

        progressBar = findViewById(R.id.progressBar)
    }

    private fun setAndStart(){
        SearchRep.text11 = text1
        SearchRep.text21 = text2
        SearchRep.text31 = text3
        SearchRep.text41 = text4
        SearchRep.text51 = text5
        SearchRep.text61 = text6
        SearchRep.text71 = text7

        val  i = Intent(this, ResultsActivity::class.java)
        startActivity(i)
        finish()
    }

    private fun check(){
        text1 = editText1.text.toString()
        text2 = editText2.text.toString()
        text3 = editText3.text.toString()
        text4 = editText4.text.toString()
        text5 = editText5.text.toString()
        text6 = editText6.text.toString()
        text7 = editText7.text.toString()

        progressBar.visibility = View.VISIBLE
        post()
    }
    private fun post(){
        val postData = JSONObject()
        try {
            postData.put("sector", text1)
            postData.put("subSector", text2)
            postData.put("technologies1Lvl", text3)
            postData.put("technologies2Lvl", text4)
            postData.put("technologies3Lvl", text5)
            postData.put("okpd2", text6)
            postData.put("description", text7)
        } catch (e: JSONException){
            Log.d("GG", e.toString())
        }
        val requestQueue = Volley.newRequestQueue(this)
        val stringRequest = object: JsonObjectRequest(Method.POST, url, postData, { response ->
            try {
                SearchRep.response = response
                progressBar.visibility = View.GONE
                setAndStart()
            } catch (e : JSONException){
                Log.d("GG", e.toString())
                progressBar.visibility = View.GONE
            }
        }, {
                error ->
            Log.d("GG", error.toString())
            progressBar.visibility = View.GONE
        }){
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/json"
                return headers
            }
        }
        requestQueue.add(stringRequest)
    }
}
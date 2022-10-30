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
    //private val key = "26a213594e7f4f6e8cd89064d885ea93"

    private lateinit var progressBar : ProgressBar
    private lateinit var editText1 : EditText
    private lateinit var editText2 : EditText
    private lateinit var editText3 : EditText
    private lateinit var editText4 : EditText
    private lateinit var editText5 : EditText
    private lateinit var editText6 : EditText
    private lateinit var editText7 : EditText
    private lateinit var btnSearch : Button
    private lateinit var btnUpload : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        btnSearch.setOnClickListener(View.OnClickListener {
            check()
        })
        btnUpload.setOnClickListener(View.OnClickListener {
            getUri()
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
        btnUpload = findViewById(R.id.btnUpload)

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
            progressBar.visibility = View.VISIBLE
            post()
        }
    }
    private fun post(){
        val postData = JSONObject()
        try {
            postData.put("sector", editText1.text.toString())
            postData.put("subSector", editText2.text.toString())
            postData.put("technologies1Lvl", editText3.text.toString())
            postData.put("technologies2Lvl", editText4.text.toString())
            postData.put("technologies3Lvl", editText5.text.toString())
            postData.put("okpd2", editText6.text.toString())
            postData.put("description", editText7.text.toString())
        } catch (e: JSONException){
            Log.d("MyLog", e.toString())
        }
        val requestQueue = Volley.newRequestQueue(this)
        val stringRequest = object: JsonObjectRequest(Method.POST, url, postData, { response ->
            try {
                Log.d("GG", response.getJSONObject("sector").toString())
                SearchRep.response = response

                progressBar.visibility = View.GONE
                setAndStart()
            } catch (e : JSONException){
                Log.d("GG", "ErrorJSON")
                Log.d("GG", e.toString())
                progressBar.visibility = View.GONE
            }
        }, {
                error ->
            Log.d("GG", "Error")
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

    private var getFilesLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        if (it.resultCode == Activity.RESULT_OK){
            val data: Intent = it.data!!
            Log.d("GG", data.data.toString())
            val file = File(data.data.toString())
        }
    }

    private fun getUri(){
        val intent = Intent().setType("*/*").setAction(Intent.ACTION_GET_CONTENT)
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.flags = FLAG_GRANT_READ_URI_PERMISSION or FLAG_GRANT_WRITE_URI_PERMISSION
        getFilesLauncher.launch(intent)
    }
}
package com.example.mls_view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import java.io.File

class StartActivity : AppCompatActivity() {

    private lateinit var btnText : Button
    private lateinit var btnFile : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        init()
        btnText.setOnClickListener(View.OnClickListener {
            val  i = Intent(this, MainActivity::class.java)
            startActivity(i)
        })
        btnFile.setOnClickListener(View.OnClickListener {
            getUri()
        })
    }

    private fun init(){
        btnText = findViewById(R.id.btnText)
        btnFile = findViewById(R.id.btnFile)
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
        intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
        getFilesLauncher.launch(intent)
    }
}
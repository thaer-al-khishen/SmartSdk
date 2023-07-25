package com.example.smartlibraryapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.smartsdk.SmartSdkHandler
import com.example.smartsdk.SmartSdkHelper

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        Log.d("ThaerOutput", SmartSdkHandler.homeScreenOutput ?: "")
        findViewById<Button>(R.id.btn_back_to_sdk).setOnClickListener {
            SmartSdkHelper.getBackToSdkWithListScreen(this)
        }
    }

}

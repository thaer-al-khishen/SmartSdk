package com.example.smartlibraryapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.smartsdk.DefaultActivity
import com.example.smartsdk.SmartSdkHandler
import com.example.smartsdk.SmartSdkHelper
import com.example.smartsdk.SmartSdkProvider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SmartSdkHelper.initiateSmartSdk(this, object : SmartSdkProvider {

//            override fun provideSmartSdkText(): String {
//                return "This is the new smart sdk text"
//            }

            override fun interceptHomeNextPageButtonActionWithActivity(): Class<out Activity> {
                return TestActivity::class.java
            }

        })

    }

}

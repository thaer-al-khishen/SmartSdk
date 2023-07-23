package com.example.smartlibraryapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.smartsdk.SmartSdkHandler
import com.example.smartsdk.SmartSdkHelper
import com.example.smartsdk.SmartSdkProvider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SmartSdkHelper().initiateSmartSdk(this, object : SmartSdkProvider {
            override fun provideSmartSdkText(): String {
                return "This is the new smart sdk text"
            }

            override fun getStringFromSdk(returnString: String) {
                Toast.makeText(this@MainActivity, "Override string from sdk", Toast.LENGTH_LONG).show()
            }

        })

    }

}

package com.example.smartsdk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SmartSdkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_smart_sdk)
        findViewById<TextView>(R.id.tv_smart_sdk_text).text = SmartSdkHandler.getInterface()?.provideSmartSdkText()
        findViewById<TextView>(R.id.btn_sdk).setOnClickListener {
            SmartSdkHandler.getInterface()?.getStringFromSdk("Clicked on button")
        }
    }

}

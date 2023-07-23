package com.example.smartsdk

import android.app.Activity
import android.content.Intent

class SmartSdkHelper {

    fun initiateSmartSdk(activity: Activity, handler: SmartSdkHandler.BaseSdkHandler) {
        SmartSdkHandler.setInterface(handler)
        activity.startActivity(Intent(activity, SmartSdkActivity::class.java))
    }

}

typealias SmartSdkProvider = SmartSdkHandler.BaseSdkHandler

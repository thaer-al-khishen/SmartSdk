package com.example.smartsdk

import android.app.Activity
import android.content.Intent

object SmartSdkHelper {

    private var sdkHandler: SmartSdkHandler.BaseSdkHandler? = null
    fun initiateSmartSdk(activity: Activity, handler: SmartSdkHandler.BaseSdkHandler) {
        SmartSdkHandler.setInterface(handler)
        sdkHandler = handler
        activity.startActivity(Intent(activity, SmartSdkActivity::class.java).apply {
            putExtra(SdkActivityKeys.SDK_ACTIVITY_START_DESTINATION_KEY, SdkActivityStartDestination.DEFAULT)
        })
    }

    fun getBackToSdkWithListScreen(activity: Activity) {
        sdkHandler?.let { SmartSdkHandler.setInterface(it) }
        activity.startActivity(Intent(activity, SmartSdkActivity::class.java).apply {
            putExtra(SdkActivityKeys.SDK_ACTIVITY_START_DESTINATION_KEY, SdkActivityStartDestination.LIST)
        })
    }

}

typealias SmartSdkProvider = SmartSdkHandler.BaseSdkHandler

object SdkActivityKeys {
    const val SDK_ACTIVITY_START_DESTINATION_KEY= "SDK_ACTIVITY_START_DESTINATION_KEY"
}

enum class SdkActivityStartDestination {
    DEFAULT, HOME, LIST
}
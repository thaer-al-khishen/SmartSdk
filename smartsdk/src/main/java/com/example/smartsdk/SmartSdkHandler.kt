package com.example.smartsdk

import android.app.Activity
import com.example.smartsdk.SmartSdkConsts.DEFAULT_VALUE

object SmartSdkHandler: BaseSmartSdkHandler<SmartSdkHandler.BaseSdkHandler>() {

    var homeScreenOutput = ""

    interface HomeScreenHandler {
        fun provideSmartSdkText(): String = DEFAULT_VALUE
        fun interceptHomeNextPageButtonActionWithActivity() : Class<out Activity>? = null

    }

    interface ListScreenHandler {
        fun onNextPageButtonClickedFromListScreen() = Unit
    }

    // Unified SdkHandler interface
    interface BaseSdkHandler : HomeScreenHandler, ListScreenHandler

}

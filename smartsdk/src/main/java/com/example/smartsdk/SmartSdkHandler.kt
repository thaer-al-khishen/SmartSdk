package com.example.smartsdk

import com.example.smartsdk.SmartSdkConsts.DEFAULT_VALUE

object SmartSdkHandler: BaseSmartSdkHandler<SmartSdkHandler.BaseSdkHandler>() {

    interface HomeScreenHandler {
        fun provideSmartSdkText(): String = DEFAULT_VALUE
        fun getStringFromSdk(returnString: String) {}
    }

    interface DetailScreenHandler {
        fun provideDetailScreenData(): String = DEFAULT_VALUE
    }

    // Unified SdkHandler interface
    interface BaseSdkHandler : HomeScreenHandler, DetailScreenHandler

}

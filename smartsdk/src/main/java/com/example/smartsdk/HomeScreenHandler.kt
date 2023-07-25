package com.example.smartsdk

internal object HomeScreenHandler: BaseSmartSdkHandler<HomeScreenHandler.HomeScreenActions>() {

    internal interface HomeScreenActions {
        fun onNextPageButtonClickedFromHomeScreen() = Unit
    }

}

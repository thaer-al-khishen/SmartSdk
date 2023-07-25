package com.example.smartsdk

object HomeScreenHandler: BaseSmartSdkHandler<HomeScreenHandler.HomeScreenActions>() {

    interface HomeScreenActions {
        fun onNextPageButtonClickedFromHomeScreen() = Unit
    }

}

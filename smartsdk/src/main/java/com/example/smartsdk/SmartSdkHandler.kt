package com.example.smartsdk

import android.app.Activity
import android.widget.TextView
import com.example.smartsdk.SmartSdkConsts.DEFAULT_VALUE
import com.example.smartsdk.databinding.FragmentHomeBinding
import com.example.smartsdk.wrappers.TextViewConfiguration
import com.example.smartsdk.wrappers.ViewConstraintsWrapper

object SmartSdkHandler: BaseSmartSdkHandler<SmartSdkHandler.BaseSdkHandler>() {

    var homeScreenOutput: String? = null
        internal set

    interface HomeScreenHandler {
        fun provideSmartSdkText(): String = DEFAULT_VALUE
        fun interceptHomeNextPageButtonActionWithActivity() : Class<out Activity>? = null

        /**
         * Provides a configuration for a given TextView and binding.
         *
         * The function accepts a TextView and a FragmentHomeBinding as parameters,
         * and returns a ViewConstraintsWrapper object that encapsulates constraints
         * for the given TextView.
         *
         * The constraints are applied to the TextView such that it's positioned
         * relative to the parent ConstraintLayout defined in the FragmentHomeBinding.
         * Margins are also applied to position the TextView within the parent layout.
         *
         * @param textView The TextView to which the constraints and new attributes will be applied.
         * @param fragmentHomeBinding The binding that holds reference to the parent ConstraintLayout.
         * @return A ViewConstraintsWrapper instance that encapsulates the constraints to be applied to the TextView.
         */
        fun provideTextViewConfiguration(textView: TextView, fragmentHomeBinding: FragmentHomeBinding): TextViewConfiguration? = null

    }

    interface ListScreenHandler {
        fun onNextPageButtonClickedFromListScreen() = Unit
    }

    // Unified SdkHandler interface
    interface BaseSdkHandler : HomeScreenHandler, ListScreenHandler

}

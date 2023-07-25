package com.example.smartlibraryapplication

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.smartsdk.SmartSdkHelper
import com.example.smartsdk.SmartSdkProvider
import com.example.smartsdk.databinding.FragmentHomeBinding
import com.example.smartsdk.wrappers.BottomConstraintModifiers
import com.example.smartsdk.wrappers.BottomConstraintWrapper
import com.example.smartsdk.wrappers.EndConstraintModifiers
import com.example.smartsdk.wrappers.EndConstraintWrapper
import com.example.smartsdk.wrappers.Margins
import com.example.smartsdk.wrappers.StartConstraintModifiers
import com.example.smartsdk.wrappers.StartConstraintWrapper
import com.example.smartsdk.wrappers.TextViewConfiguration
import com.example.smartsdk.wrappers.TopConstraintModifiers
import com.example.smartsdk.wrappers.TopConstraintWrapper
import com.example.smartsdk.wrappers.ViewConstraintsWrapper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SmartSdkHelper.initiateSmartSdk(this, object : SmartSdkProvider {

            override fun interceptHomeNextPageButtonActionWithActivity(): Class<out Activity> {
                return TestActivity::class.java
            }

            override fun provideTextViewConfiguration(
                textView: TextView,
                fragmentHomeBinding: FragmentHomeBinding
            ): TextViewConfiguration {

                val constraints = ViewConstraintsWrapper(
                    startConstraint = StartConstraintWrapper(
                        StartConstraintModifiers.START_TO_START_OF,
                        fragmentHomeBinding.clParent
                    ),
                    topConstraint = TopConstraintWrapper(
                        TopConstraintModifiers.TOP_TO_TOP_OF,
                        fragmentHomeBinding.clParent
                    ),
                    bottomConstraint = BottomConstraintWrapper(
                        BottomConstraintModifiers.BOTTOM_TO_BOTTOM_OF,
                        fragmentHomeBinding.clParent
                    ),
                    endConstraint = EndConstraintWrapper(
                        EndConstraintModifiers.END_TO_END_OF,
                        fragmentHomeBinding.clParent
                    ),
                    margins = Margins(top = 20f)
                )

                textView.apply {
                    text = "text from activity"
                }

                return TextViewConfiguration(textView, constraints)
            }
        })

    }

}

package com.example.smartsdk

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.smartsdk.databinding.FragmentHomeBinding
import com.example.smartsdk.wrappers.applyNewConstraints

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            tvHome.text = SmartSdkHandler.getInterface()?.provideSmartSdkText()

            btnHomeNextPage.setOnClickListener {
                HomeScreenHandler.getInterface()?.onNextPageButtonClickedFromHomeScreen()
            }

            SmartSdkHandler.getInterface()?.provideTextViewConfiguration(binding.tvHome, binding)?.run {
                    this.viewConstraintsWrapper?.run {
                        binding.tvHome.applyNewConstraints(this)
                    }
                }

        }
    }

}

package com.example.smartsdk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.smartsdk.SmartSdkHandler.homeScreenOutput

class SmartSdkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_smart_sdk)

        val sdkActivityStartDestination = if (intent?.hasExtra(SdkActivityKeys.SDK_ACTIVITY_START_DESTINATION_KEY) == true) {
            intent?.safelyGetSerializableExtra<SdkActivityStartDestination>(SdkActivityKeys.SDK_ACTIVITY_START_DESTINATION_KEY)
        } else null

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.smart_sdk_nav_controller) as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.nav_graph)

        when(sdkActivityStartDestination) {
            SdkActivityStartDestination.DEFAULT -> graph.setStartDestination(R.id.homeFragment)
            SdkActivityStartDestination.HOME -> graph.setStartDestination(R.id.homeFragment)
            SdkActivityStartDestination.LIST -> graph.setStartDestination(R.id.listFragment)
            else -> graph.setStartDestination(R.id.homeFragment)
        }

        val navController = navHostFragment.navController
        navController.setGraph(graph, intent.extras)

        HomeScreenHandler.setInterface(object : HomeScreenHandler.HomeScreenActions {
            override fun onNextPageButtonClickedFromHomeScreen() {
                val overridenActivity = SmartSdkHandler.getInterface()?.interceptHomeNextPageButtonActionWithActivity()
                if (overridenActivity != null) {
                    SmartSdkHandler.getInterface()?.interceptHomeNextPageButtonActionWithActivity()?.let { interceptingActivity ->
                        homeScreenOutput = "Home Screen Output"
                        startActivity(
                            Intent(this@SmartSdkActivity, interceptingActivity)
                        )
                    }
                } else if (navController.currentDestination?.id == R.id.homeFragment) {
                    navController.navigate(HomeFragmentDirections.actionHomeFragmentToListFragment())
                }

            }
        })

    }

}

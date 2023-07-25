package com.example.smartsdk

import android.content.Intent
import android.os.Build

inline fun <reified T: java.io.Serializable> Intent.safelyGetSerializableExtra(key: String): T? {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        return this.getSerializableExtra(key, T::class.java)
    } else {
        return this.getSerializableExtra(key) as? T
    }
}

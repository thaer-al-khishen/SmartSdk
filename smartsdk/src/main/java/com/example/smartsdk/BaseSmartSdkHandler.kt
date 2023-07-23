package com.example.smartsdk

open class BaseSmartSdkHandler<T> {

    private var mInterface: T? = null

    fun setInterface(inter: T) {
        mInterface = inter
    }

    fun getInterface(): T? = mInterface

}
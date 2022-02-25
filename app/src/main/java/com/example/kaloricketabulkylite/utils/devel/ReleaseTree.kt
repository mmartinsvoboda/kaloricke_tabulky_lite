package com.example.kaloricketabulkylite.utils.devel

import android.annotation.SuppressLint
import android.util.Log
import timber.log.Timber

class ReleaseTree : Timber.Tree() {

    private val maxLogLength: Int = 4000

    @SuppressLint("LogNotTimber")
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (isLoggable(tag, priority)) {

            if (message.length < maxLogLength) {
                if (priority == Log.ASSERT) {
                    Log.wtf(tag, message)
                } else {
                    Log.println(priority, tag, message)
                }
                return
            }

            message.chunked(maxLogLength).forEach {
                if (priority == Log.ASSERT) {
                    Log.wtf(tag, it)
                } else {
                    Log.println(priority, tag, it)
                }
            }
        }
    }

    override fun isLoggable(tag: String?, priority: Int): Boolean =
        !(priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO)
}
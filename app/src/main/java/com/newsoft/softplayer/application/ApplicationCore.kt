package com.newsoft.softplayer.application

import android.app.Application
import android.content.Intent
import com.newsoft.softplayer.framework.common.IMapper
import com.newsoft.softplayer.framework.common.MapperFactory
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject


@HiltAndroidApp
class ApplicationCore @Inject constructor(): Application() {

    override fun onCreate() {
        super.onCreate()

        val mappers = ArrayList<IMapper<Any, Any>>()

        MapperFactory(mappers)

        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            handleUncaughtException (thread, throwable);
        }

    }

    fun handleUncaughtException(thread: Thread?, e: Throwable) {
        e.printStackTrace() // not all Android versions will print the stack trace automatically
        val intent = Intent()
        intent.action = "com.mydomain.SEND_LOG" // see step 5.
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK // required when starting from Application
        startActivity(intent)
        System.exit(1) // kill off the crashed app
    }


}
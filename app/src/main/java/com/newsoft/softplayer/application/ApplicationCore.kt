package com.newsoft.softplayer.application

import android.app.Application
import com.newsoft.softplayer.framework.common.IMapper
import com.newsoft.softplayer.framework.common.MapperFactory

class ApplicationCore: Application() {


    override fun onCreate() {
        super.onCreate()

        val mappers = ArrayList<IMapper<Any, Any>>()

        MapperFactory(mappers)

    }

}
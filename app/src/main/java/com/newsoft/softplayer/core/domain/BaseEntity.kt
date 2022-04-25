package com.newsoft.softplayer.core.domain

import java.util.*

abstract class BaseEntity () {
     var id:String;
     lateinit var createdAt:Date;
     lateinit var modifiedAt:Date;
    init {
        id = UUID.randomUUID().toString();
    }
}
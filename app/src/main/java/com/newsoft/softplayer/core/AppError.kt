package com.newsoft.softplayer.core

class AppError(val title: String,val code:Int = 500,msg:String) : Exception(msg) {

    constructor(title: String, code:Int = 500) : this(title,code,"")

    constructor(title:String) : this(title,500,"")


}
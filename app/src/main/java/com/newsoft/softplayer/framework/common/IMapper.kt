package com.newsoft.softplayer.framework.common

interface IMapper<Source, Target> {

    fun toSource(target: Target): Source

    fun toTarget(source: Source): Target

}
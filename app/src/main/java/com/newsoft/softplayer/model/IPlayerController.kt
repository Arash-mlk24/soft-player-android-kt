package com.newsoft.softplayer.model

interface IPlayerController {

    fun loadMedia(resourceId: Int): Unit

    fun release(): Unit

    fun isPlaying(): Boolean

    fun play(): Unit

    fun reset(): Unit

    fun pause(): Unit

    fun initializeProgressCallback(): Unit

    fun seekTo(position: Int): Unit

}
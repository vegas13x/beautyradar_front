package com.nick_sib.beauty_radar.model.repository.core

interface Repository<S> {
    fun getCalendar(): S
    suspend fun getService(): S
}
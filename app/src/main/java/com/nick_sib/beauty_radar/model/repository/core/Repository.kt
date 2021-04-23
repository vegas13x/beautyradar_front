package com.nick_sib.beauty_radar.model.repository.core

interface Repository<S> {
    fun getData(): S
}
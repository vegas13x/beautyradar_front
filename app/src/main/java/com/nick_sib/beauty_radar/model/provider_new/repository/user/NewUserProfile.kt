package com.nick_sib.beauty_radar.model.provider_new.repository.user

data class NewUserProfile (
    private val user: User,
    private val server: CodeServer,
    private val message: CodeMessage)
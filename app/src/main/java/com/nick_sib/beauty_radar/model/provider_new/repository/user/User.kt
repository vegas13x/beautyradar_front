package com.nick_sib.beauty_radar.model.provider_new.repository.user

data class User(
    private var email: String,
    private var id: String,
    private var img: String,
    private var login: String,
    private var master: List<UserMaster>,
    private var name: String,
    private var phone: String,
    private var rating: String,
    private var upn: String)

